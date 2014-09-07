package com.mkl.websuites.internal.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.webbitserver.WebbitException;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.ext.Customization;
import com.mkl.websuites.ext.ServiceDefinition;
import com.mkl.websuites.ext.ServiceDefinition.Service;


@Slf4j
public class ServiceFactory {

	


	private static boolean isInitialized = false;
	
	private static Map<Class<?>, Class<?>> instanceMap;
	
	
	
	
	
	public static void init(Class<?> runnerClass) {
		
		isInitialized = true;
		
		instanceMap = new HashMap<Class<?>, Class<?>>();
		
		ServiceDefinition defaultServiceDefinition =
				DefaultServiceDefinitions.class.getAnnotation(ServiceDefinition.class);
		
		Service[] services = defaultServiceDefinition.value();
		
		for (Service service : services) {
			instanceMap.put(service.service(), service.implementation());
		}
		
		applyServiceOverridesFrom(runnerClass);
		
		log.debug("service factory initialized");
	}
	
	
	
	
	
	
	
	private static void applyServiceOverridesFrom(Class<?> runnerClass) {
		
		WebSuitesConfig config = runnerClass
				.getAnnotation(WebSuitesRunner.class)
				.configurationClass()
				.getAnnotation(WebSuitesConfig.class);
		
		Class<?> overridesDef = config.serviceOverrides();
		
		if (overridesDef != null) {
			
			Customization customization = overridesDef.getAnnotation(Customization.class);
			
			if (customization == null) {
				log.error("customization class must be annotated with Customization annotation");
				throw new WebbitException("customization class must be "
						+ "annotated with Customization annotation");
			}
			
			Service[] serviceOverrides = customization.serviceOverrides();
			
			for (Service override  : serviceOverrides) {
				log.debug("applied service definition override for " + override.service()
						+ " with impl: " + override.implementation());
				instanceMap.put (override.service(), override.implementation());
			}
			
		}
	}


	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> serviceClass) {
		
		if (!isInitialized) {
			log.error("trying to acquire service before service factory us initialized");
			throw new WebbitException("Trying to acquire service " + serviceClass.getName() +
					" before service factory is initialized");
		}
		
		
		try {
			return (T) instanceMap.get(serviceClass).getMethod("getInstance").invoke(null);
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			
			log.error("cannot make instance for service: " + serviceClass.getName() +
					", erro: " + e.getLocalizedMessage());
			
			throw new WebbitException("Cannot instantiate service: " + serviceClass.getName() +
					", erro: " + e.getLocalizedMessage() +
					". Make sure the service has public static getInstance() method.");
			
		}
		
	}
	
}
