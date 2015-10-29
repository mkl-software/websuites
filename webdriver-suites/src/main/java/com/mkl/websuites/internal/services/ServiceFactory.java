package com.mkl.websuites.internal.services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;



@Slf4j
public class ServiceFactory {

	


	private static boolean isInitialized = false;
	
	private static Map<Class<?>, Class<?>> instanceMap;
	
	
	
	
	
	public static void init() {
		
		if (isInitialized) {
			//throw new WebSuitesException("Cannot initialize ServiceFactory more than once.");
			log.warn("Trying to initialize ServiceFactory more than once");
			// TODO: throw or not WSE? not a big case to allow init more than once...
		}
		
		isInitialized = true;
		
		instanceMap = new HashMap<Class<?>, Class<?>>();
		
		ServiceDefinition defaultServiceDefinition =
				DefaultServiceDefinitions.class.getAnnotation(ServiceDefinition.class);
		
		Service[] services = defaultServiceDefinition.value();
		
		for (Service service : services) {
			instanceMap.put(service.service(), service.implementation());
		}
		
		
		applyServiceOverridesFrom();
		
		log.debug("service factory initialized");
	}
	
	
	
	
	
	
	
	private static void applyServiceOverridesFrom() {
		
		Service[] serviceOverrides = WebSuitesConfig.get().extension().serviceOverrides();
		
		for (Service override  : serviceOverrides) {
			log.debug("applied service definition override for " + override.service()
					+ " with impl: " + override.implementation());
			instanceMap.put (override.service(), override.implementation());
		}
			
	}


	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> serviceClass) {
		
		if (!isInitialized) {
			log.error("trying to acquire service before service factory is initialized");
			throw new WebSuitesException("Trying to acquire service " + serviceClass.getName() +
					" before service factory is initialized");
		}
		
		
		try {
			Method factoryMethod = instanceMap.get(serviceClass).getDeclaredMethod("getInstance");
			if (factoryMethod == null) {
				throw new WebSuitesException("Service " + serviceClass + " must implement public "
						+ "static getInstance() factory method to provide service instances.");
			}
			return (T) factoryMethod.invoke(null);
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			log.error("cannot make instance for service: " + serviceClass.getName() +
					", error: " + e.getLocalizedMessage());
			
			throw new WebSuitesException("Cannot instantiate service: " + serviceClass.getName() +
					", error: " + e.getLocalizedMessage() +
					". Make sure the service has public static getInstance() method.");
			
		} catch (Exception e) {
			
			throw new WebSuitesException("", e);
		}
		
	}
	
}
