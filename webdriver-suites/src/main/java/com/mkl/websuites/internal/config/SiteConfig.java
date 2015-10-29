package com.mkl.websuites.internal.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface SiteConfig {

	String host() default "localhost";

	int port() default 8080;

	String basePath() default "/";

	int waitTimeout() default 10;
	
	boolean doNotCloseBrowserAtTheEnd() default false;
}
