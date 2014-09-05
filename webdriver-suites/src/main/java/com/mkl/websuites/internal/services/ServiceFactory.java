package com.mkl.websuites.internal.services;

import lombok.extern.slf4j.Slf4j;

import org.webbitserver.WebbitException;

import se.jbee.inject.Dependency;
import se.jbee.inject.Injector;
import se.jbee.inject.bootstrap.Bootstrap;
import se.jbee.inject.bootstrap.BootstrapperBundle;


@Slf4j
public class ServiceFactory {

	
	private static class WebSuitesBundle extends BootstrapperBundle {
	
	    @Override
	    protected void bootstrap() {
	    	
	        install(FrameworkConfiguration.class );
	    }
	}


	private static boolean isInitialized = false;
	
	private static Injector injector;
	
	
	
	
	
	public static void init(Class<?> runnerClass) {
		
		isInitialized = true;
		
		injector = Bootstrap.injector(WebSuitesBundle.class );
		
		log.debug("service factory initialized");
	}
	
	
	public static <T> T get(Class<T> serviceClass) {
		if (!isInitialized) {
			log.error("trying to acquire service before service factory us initialized");
			throw new WebbitException("Trying to acquire service " + serviceClass.getName() +
					" before service factory is initialized");
		}
		
		Dependency<T> dependency = Dependency.dependency(serviceClass);
		
		return injector.resolve(dependency);
	}
	
}
