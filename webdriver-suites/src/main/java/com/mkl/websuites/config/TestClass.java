package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import junit.framework.Test;


@Retention(RetentionPolicy.RUNTIME)
public @interface TestClass {


    Class<? extends Test> value();
}
