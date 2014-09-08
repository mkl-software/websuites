package com.mkl.websuites.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.internal.services.ServiceDefinition.Service;



@Retention(RetentionPolicy.RUNTIME)
public @interface Customization {

	Service[] serviceOverrides() default {};
	
	String[] customCommandScanPackages() default {};

}
