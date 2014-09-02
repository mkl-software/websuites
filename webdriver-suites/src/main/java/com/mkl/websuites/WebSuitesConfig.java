package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuitesConfig {

	String host();

	int port() default 80;

	String basePath();

	String[] browsers();

	int waitTimeout() default 30;

}
