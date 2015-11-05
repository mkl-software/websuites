package com.mkl.websuites.internal.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescriptor {

    String name();

    @SuppressWarnings("rawtypes")
    Class[] argumentTypes() default {};

}
