package com.mkl.websuites.internal.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.BaseMultiBrowserSuite;


@Retention(RetentionPolicy.RUNTIME)
public @interface WebdriverSuites {

	Class<?> configurationClass();

	Class<? extends BaseMultiBrowserSuite>[] suite();


}
