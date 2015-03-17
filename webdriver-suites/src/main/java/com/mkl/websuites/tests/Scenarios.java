package com.mkl.websuites.tests;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface Scenarios {

	String[] value();

}
