package com.mkl.websuites.internal.config;

import com.mkl.websuites.internal.services.ServiceDefinition.Service;

public @interface Extension {

	String[] commandExtensionPackages() default {};
	
	Service[] serviceOverrides() default {};
}
