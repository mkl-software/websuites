package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.internal.WebSuitesException;

public abstract class ClassParameterValidator  implements ParameterValueValidator {

	public ClassParameterValidator() {
		super();
	}

	@Override
	public void validateParam(String paramValue) {
		
		try {
			Class<?> classToInstantiate = Class.forName(paramValue);
			Class<?> targetExpectedSuperClass = getTargetClassForInstantiation();
			if (!targetExpectedSuperClass.isAssignableFrom(classToInstantiate)) {
				throw new WebSuitesException("Specified class " + paramValue +
						" must implement " + targetExpectedSuperClass.getName());
			}
			classToInstantiate.newInstance();
		} catch (ClassNotFoundException e) {
			throw new WebSuitesException("Cannot find specified class: " + paramValue);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new WebSuitesException("Cannot instantiate specified class: " + paramValue +
					" Make sure the class has a no-arg constructor.");
		}
	
	}

	protected abstract Class<?> getTargetClassForInstantiation();

}