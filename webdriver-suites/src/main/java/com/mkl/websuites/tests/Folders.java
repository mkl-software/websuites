package com.mkl.websuites.tests;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface Folders {

	String[] path();
	
	boolean ignoreSubfolders() default false;
}
