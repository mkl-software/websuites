package com.mkl.websuites.internal.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.GenericSuite;


@Retention(RetentionPolicy.RUNTIME)
public @interface WebdriverSuites {

	Class<?> configurationClass();

	Class<? extends GenericSuite>[] suite();


}
