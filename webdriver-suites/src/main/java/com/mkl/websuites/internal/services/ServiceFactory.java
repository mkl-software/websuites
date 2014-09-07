package com.mkl.websuites.internal.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.webbitserver.WebbitException;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.impl.BrowserControllerImpl;
import com.mkl.websuites.internal.impl.ConfigurationManagerImpl;


@Slf4j
public class ServiceFactory {

	


	private static boolean isInitialized = false;
	
	private static Map<Class<?>, Class<?>> instanceMap;
	
	
	
	
	
	public static void init(Class<?> runnerClass) {
		
		isInitialized = true;
		
		instanceMap = new HashMap<Class<?>, Class<?>>();
		
		instanceMap.put(BrowserController.class, BrowserControllerImpl.class);
		instanceMap.put(ConfigurationManager.class, ConfigurationManagerImpl.class);
		
		log.debug("service factory initialized");
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
