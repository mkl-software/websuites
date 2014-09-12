package com.mkl.websuites.internal.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.impl.SampleCommand;



@SuppressWarnings("rawtypes")
public class StandardCommandBuilder implements CommandBuilder {

	
	
	private static StandardCommandBuilder instance = new StandardCommandBuilder();
	
	private Map<String, Constructor> commandConstructorMap;

	private Map<String, List<Class>> commandTypesMap;

	
	
	public static StandardCommandBuilder getInstance() {
		return instance ;
	}
	
	
	
	public StandardCommandBuilder() {
		
		 buildCommandConstructorMap();
		 buildCommandArgumentTypesMap();
	}
	
	
	
	
	protected void buildCommandArgumentTypesMap() {
		commandTypesMap = new HashMap<>();
		
		List<Class> argTypes = Arrays.asList((Class) String.class);
		
		commandTypesMap.put("sample", argTypes);
	}



	protected void buildCommandConstructorMap() {
		
		commandConstructorMap = new HashMap<>();
		
		List<Class> argTypes = Arrays.asList((Class) String.class);
		
		try {
			commandConstructorMap.put("sample", SampleCommand
					.class
					.getConstructor(argTypes.toArray(new Class[] {})));
			
		} catch (NoSuchMethodException | SecurityException e) {
			
			throw new WebSuitesException("Error in command configuration");
		}
		
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



	protected List<Object> convertArgumentsToProperTypes(String[] arguments,
			List<Class> argumentTypes) {
		
		List<Object> argumentValues = new ArrayList<Object>();
		
		List resolvableWithValueOf =
				Arrays.asList(Boolean.class, Integer.class, Float.class,
						Double.class, Long.class, Short.class);
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
