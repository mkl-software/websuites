package com.mkl.websuites;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.mkl.websuites.internal.WebSuitesException;



public class WebSuitesUserProperties {

	
	
	private static WebSuitesUserProperties instance;
	
	public static WebSuitesUserProperties get() {
		// late initialization + must be able to be rest for unit tests
		if (instance == null) {
			instance = new WebSuitesUserProperties();
		}
		return instance;
		
	}
	
	
	
	private  Map<String, String> globalProperties = new HashMap<String, String>();
	
	
	
	public WebSuitesUserProperties() {
		// populate system properties:
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			globalProperties.put("env." + key, properties.getProperty(key.toString()));
		}
	}
	
	
	public void load(InputStream source) {
		Properties props = new Properties();
		try {
			props.load(source);
			
			for (Object key : props.keySet()) {
				String s = key.toString();
				globalProperties.put(s, props.getProperty(s));
			}
			
		} catch (IOException e) {
			throw new WebSuitesException("Cannot load specified properties file", e);
		}
	}
	
	
	public void populateFrom(Map<String, String> properties) {
		
		for (String key : properties.keySet()) {
			globalProperties.put(key, properties.get(key));
		}
	}
	
	
	public boolean isSet(String name) {
		return globalProperties.get(name) != null;
	}
	
	
	public void unset(String name) {
		globalProperties.put(name, null);
	}
	
	/**
	 * Use with care!
	 */
	public void clear() {
		globalProperties.clear();
	}
	
	
	public void setProperty(String name, String value) {
		globalProperties.put(name, value);
	}
	
	
	public String getProperty(String  name) {
		return globalProperties.get(name);
	}
	
	public String getProperty(String  name, String defaultValue) {
		String value = getProperty(name);
		return value != null && !value.isEmpty() ? value : defaultValue;
	}
	
	
	
	public int getNumberProperty(String  name) {
		String value = getProperty(name);
		
		int numericValue;
		try {
			numericValue = Integer.valueOf(value);
		} catch (NumberFormatException e) {
			
			String msg = "Error while converting numeric value for property: \""
					+ name + "\" with value: " + value;
			
			throw new WebSuitesException(msg, e);
		}
		return numericValue;
	}
	
	
	
	public int getNumberProperty(String  name, int defaultValue) {
		
		return getNumberProperty(getProperty(name, defaultValue + ""));
	}
	
	
	
	
	public boolean getBooleanProperty(String  name) {
		
		String value = getProperty(name);
		
		boolean boolValue;
		try {
			boolValue = Boolean.valueOf(value);
		} catch (NumberFormatException e) {
			
			String msg = "Error while converting numeric value for property: \""
					+ name + "\" with value: " + value;
			
			throw new WebSuitesException(msg, e);
		}
		return boolValue;
	}
	
	
	
	public boolean getBooleanProperty(String  name, boolean defaultValue) {
		
		return getBooleanProperty(getProperty(name, defaultValue + ""));
	}
	
	
}
