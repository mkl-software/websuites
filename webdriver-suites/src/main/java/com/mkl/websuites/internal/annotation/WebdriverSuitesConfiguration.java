package com.mkl.websuites.internal.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface WebdriverSuitesConfiguration {

	String host();

	int port();

	String basePath();

	String[] browsers();

}
