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

import com.mkl.websuites.WebSuitesException;



@Slf4j
@SuppressWarnings("rawtypes")
public class StandardCommandBuilder implements CommandBuilder {

	
	
	private static StandardCommandBuilder instance = new StandardCommandBuilder();
	
	private Map<String, Constructor> commandConstructorMap;

	private Map<String, List<Class>> commandTypesMap;

	
	
	public static StandardCommandBuilder getInstance() {
		return instance ;
	}
	
	
	
	public StandardCommandBuilder() {
		
		scanClasspathForCommands();
	}
	
	
	
	
	@Override
	public Command instantiateCommand(String commandName, String[] arguments) {
		
		Constructor commandConstructor = commandConstructorMap.get(commandName);
		
		List<Class> argumentTypes = commandTypesMap.get(commandName);
		
		if (arguments.length != argumentTypes.size()) {
			
			throw new WebSuitesException("Failed to create command: " + commandName +
					" - invalid argument list " + Arrays.toString(arguments) +
					" for expected argument types " + commandConstructor);
		}
		
		List<Object> commandArguments = convertArgumentsToProperTypes(arguments, argumentTypes);
		
		try {
			Command command = (Command) commandConstructor.newInstance(commandArguments.toArray());
			
			return command;
			
		} catch (Exception e) {
			
			throw new WebSuitesException("");
		}
		
	}



	protected void scanClasspathForCommands() {
		
		Reflections reflections = new Reflections("com.mkl.websuites");

		Set<Class<?>> allCommandsInClasspath = 
				reflections.getTypesAnnotatedWith(CommandDescriptor.class);
		
		commandTypesMap = new HashMap<>();
		commandConstructorMap = new HashMap<>();

		log.debug("classpath scanned with reflection for annotated command,"
				+ " found {} commands", allCommandsInClasspath.size());
		
		
		for (Class<?> commandClass : allCommandsInClasspath) {
			
			CommandDescriptor commandDescriptor = commandClass.getAnnotation(CommandDescriptor.class);
			
			List<Class> argumentTypes = Arrays.asList(commandDescriptor.argumentTypes());
			
			commandTypesMap.put(commandDescriptor.name(),
							argumentTypes);
			
			try {
				
				Constructor constructor =
						commandClass.getConstructor(argumentTypes.toArray(new Class[] {}));
				commandConstructorMap.put(commandDescriptor.name(), constructor);
				
			} catch (NoSuchMethodException | SecurityException e) {
				
				throw new WebSuitesException("cannot find constructor for command + "  +
						commandDescriptor.name() + " with annotated argument list: " + argumentTypes);
			}
		}
	}





	@SuppressWarnings("unchecked")
	protected List<Object> convertArgumentsToProperTypes(String[] arguments,
			List<Class> argumentTypes) {
		
		List<Object> argumentValues = new ArrayList<Object>();
		
		List resolvableWithValueOf =
				Arrays.asList(Boolean.class, Integer.class, Float.class,
						Double.class, Long.class, Short.class, Byte.class);
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
					// will never occur!
					throw new WebSuitesException("something wrong - cannot cast value of argument "
					+ argument + " to type " + argType + " with standard valueOf() call");
				}
			}
		}
		
		return argumentValues;
	}

}
