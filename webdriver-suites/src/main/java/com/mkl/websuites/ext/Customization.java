package com.mkl.websuites.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface Customization {

	ServiceDefinition[] serviceOverrides();

}
