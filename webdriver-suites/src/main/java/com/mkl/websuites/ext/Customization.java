package com.mkl.websuites.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.ext.ServiceDefinition.Service;



@Retention(RetentionPolicy.RUNTIME)
public @interface Customization {

	Service[] serviceOverrides();

}
