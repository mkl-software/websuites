package com.mkl.websuites.internal.services;

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
