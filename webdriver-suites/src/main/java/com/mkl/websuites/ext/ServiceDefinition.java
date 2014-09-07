package com.mkl.websuites.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceDefinition {

	
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Service {

		Class<?> service();

		Class<?> implementation();

		
	}

	Service[] value();
}
