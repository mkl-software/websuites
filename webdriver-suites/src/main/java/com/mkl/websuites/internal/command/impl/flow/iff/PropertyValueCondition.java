package com.mkl.websuites.internal.command.impl.flow.iff;

import com.mkl.websuites.WebSuitesUserProperties;

public  class PropertyValueCondition implements IfCondition {

	
	private String propertyName;
	
	private PropertyValueAcceptor valueAcceptor;
	
	
	public PropertyValueCondition(String propertyName) {
		super();
		this.propertyName = propertyName;
	}


	@Override
	public boolean isConditionMet() {

		WebSuitesUserProperties props = WebSuitesUserProperties.get();
		String actualValue = props.getProperty(propertyName);
		
		return valueAcceptor.accept(propertyName, actualValue);
	}


	public void setValueAcceptor(PropertyValueAcceptor valueAcceptor) {
		this.valueAcceptor = valueAcceptor;
	}


	
}
