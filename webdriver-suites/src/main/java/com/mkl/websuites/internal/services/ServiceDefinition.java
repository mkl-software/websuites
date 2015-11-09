package com.mkl.websuites.internal.services;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.config.Service;



@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceDefinition {


    Service[] value();
}
