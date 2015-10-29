package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.internal.services.ServiceDefinition.Service;


@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {

	String[] commandExtensionPackages() default {};
	
	Service[] serviceOverrides() default {};
}
