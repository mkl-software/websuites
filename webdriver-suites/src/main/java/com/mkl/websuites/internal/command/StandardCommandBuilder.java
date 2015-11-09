package com.mkl.websuites.internal.command;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.reflections.Reflections;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.command.ParameterizedCommand;
import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.scenario.SourceLine;



@Slf4j
@SuppressWarnings("rawtypes")
public class StandardCommandBuilder implements CommandBuilder {



    private static StandardCommandBuilder instance;

    private Map<String, Constructor> commandConstructorMap;
    private Map<String, Constructor> parameterizedCommandConstructorMap;

    private Map<String, List<Class>> commandTypesMap;

    // exposed (via deencapsulation) to unit tests
    private static String[] customScanPathPackages = {"com.mkl.websuites.itests.cmd"};

    public static StandardCommandBuilder getInstance() {
        if (instance == null) {
            instance = new StandardCommandBuilder();
        }
        return instance;
    }



    public StandardCommandBuilder() {

        Set<Class<?>> allCommandsInClasspath = scanClasspathForCommands();

        commandTypesMap = new HashMap<>();
        commandConstructorMap = new HashMap<>();
        parameterizedCommandConstructorMap = new HashMap<>();

        for (Class<?> commandClass : allCommandsInClasspath) {

            commandTypesMap.putAll(populateCommandInformation(commandClass));
        }
    }



    @Override
    public Command instantiateCommand(String commandName, String[] arguments, SourceLine sourceLine) {

        if (!commandTypesMap.containsKey(commandName)) {
            throw new WebSuitesException(String.format("Command named '%s' doesn't have corresponding implementation, "
                    + "please make sure there is a class annotated with @CommandDescriptor and located in the "
                    + "command scan path.\nProblem found in source file:\n%s", commandName,
                    sourceLine.printSourceInfo()));
        }

        Constructor commandConstructor;

        boolean areParametersInMapFormat = checkIfArgumentsAreParametersMap(arguments);

        if (areParametersInMapFormat) {

            commandConstructor = parameterizedCommandConstructorMap.get(commandName);

        } else {

            commandConstructor = commandConstructorMap.get(commandName);
        }

        if (commandConstructor == null) {
            throw new WebSuitesException("Failed to create command \"" + commandName
                    + "\" - command class doesn't have constructor for arguments " + Arrays.toString(arguments));
        }

        List<Class> argumentTypes = commandTypesMap.get(commandName);

        if (!areParametersInMapFormat && arguments.length != argumentTypes.size()) {

            throw new WebSuitesException("Failed to create command: " + commandName + " - invalid argument list "
                    + Arrays.toString(arguments) + " for expected argument types " + argumentTypes);
        }

        List<Object> commandArguments;

        if (areParametersInMapFormat) {

            commandArguments = convertArgumentsToParameterMap(arguments);
        } else {

            commandArguments = convertArgumentsToProperTypes(arguments, argumentTypes);
        }

        try {

            Command command = (Command) commandConstructor.newInstance(commandArguments.toArray());

            return command;

        } catch (Exception e) {

            throw new WebSuitesException("Cannot instantiate command " + commandName + " using constructor "
                    + commandConstructor + " for arguments " + commandArguments, e);
        }

    }



    // TODO: for now it will detect parameter map only if all tokens contain at least
    // one "=" char. Change it to accept various combinations. Resolve situation when
    // command has n arguments and one passes n parameters in the map - how to differentiate?
    protected boolean checkIfArgumentsAreParametersMap(String[] arguments) {

        if (arguments.length == 0) {
            return false;
        }
        for (String arg : arguments) {
            if (arg.indexOf("=") == -1) {
                return false;
            }
        }
        return true;
    }



    protected Set<Class<?>> scanClasspathForCommands() {

        String defaultScanPackage = "com.mkl.websuites.internal.command.impl";

        List<Object> scanPathElements = new ArrayList<>();

        scanPathElements.add(defaultScanPackage);

        for (Object packageElement : customScanPathPackages) {
            scanPathElements.add(packageElement);
        }
        Reflections reflections = new Reflections(scanPathElements.toArray());

        // honorInherited=true to skip inherited but not annotated classes
        Set<Class<?>> allCommandsInClasspath = reflections.getTypesAnnotatedWith(CommandDescriptor.class, true);

        log.debug("classpath scanned with reflection for annotated command," + " found {} commands",
                allCommandsInClasspath.size());

        return allCommandsInClasspath;
    }



    protected Map<String, List<Class>> populateCommandInformation(Class<?> commandClass) {

        Map<String, List<Class>> commandTypesMap = new HashMap<>();

        CommandDescriptor commandDescriptor = commandClass.getAnnotation(CommandDescriptor.class);

        // apparently might be null - when inheriting from another annotated command the class is
        // recognized
        // from Reflections lib, but in fact it doesn't have its own annotation.
        if (commandDescriptor == null) {
            throw new WebSuitesException("Command class " + commandClass.getName() + " must be annotaded with "
                    + "a proper CommandDescriptor annotation.");
        }
        String commandName = commandDescriptor.name();

        List<Class> argumentTypes = Arrays.asList(commandDescriptor.argumentTypes());

        commandTypesMap.put(commandName, argumentTypes);

        resolveStandardCommandConstructor(commandClass, commandName, argumentTypes);

        resolveParameterizedCommandConstructor(commandClass, commandName);

        return commandTypesMap;
    }



    protected void resolveStandardCommandConstructor(Class<?> commandClass, String commandName,
            List<Class> argumentTypes) {
        try {

            Constructor constructor = commandClass.getConstructor(argumentTypes.toArray(new Class[] {}));
            commandConstructorMap.put(commandName, constructor);

        } catch (NoSuchMethodException | SecurityException e) {

            throw new WebSuitesException("Cannot find constructor for command " + commandName
                    + " with annotated argument list: " + argumentTypes);
        }
    }



    protected void resolveParameterizedCommandConstructor(Class<?> commandClass, String commandName) {

        if (ParameterizedCommand.class.isAssignableFrom(commandClass)) {
            // check for Map<String, String> constructor:
            try {

                Constructor constructor = commandClass.getConstructor(Map.class);
                parameterizedCommandConstructorMap.put(commandName, constructor);

            } catch (NoSuchMethodException | SecurityException e) {

                throw new WebSuitesException("Cannot find constructor with "
                        + "Map<String, String> parameter for parameterized command:  " + commandName);
            }
        }
    }



    protected List<Object> convertArgumentsToParameterMap(String[] arguments) {

        Map<String, String> resultMap = new HashMap<>();

        for (String arg : arguments) {

            String[] tokens = arg.split("=", 2);
            resultMap.put(tokens[0], tokens.length == 2 ? tokens[1] : "");
        }
        return Arrays.asList((Object) resultMap);
    }



    /**
     * Converts lists of string command arguments to strong-typed values of given types in
     * argumentTypes.
     */
    @SuppressWarnings("unchecked")
    protected List<Object> convertArgumentsToProperTypes(String[] arguments, List<Class> argumentTypes) {

        List<Object> argumentValues = new ArrayList<Object>();

        List resolvableWithValueOf =
                Arrays.asList(Boolean.class, Integer.class, Float.class, Double.class, Long.class, Short.class,
                        Byte.class);
        for (int i = 0; i < arguments.length; i++) {

            Class argType = argumentTypes.get(i);
            String argument = arguments[i];

            if (argType.equals(String.class)) {
                argumentValues.add(argument);
            }

            if (resolvableWithValueOf.contains(argType)) {
                try {
                    argumentValues.add(argType.getMethod("valueOf", String.class).invoke(null, argument));
                } catch (Exception e) {
                    throw new WebSuitesException("Error while converting command parameter: cannot cast value "
                            + "of argument '" + argument + "' to type " + argType + " with standard valueOf() call");
                }
            }
        }

        return argumentValues;
    }

}
