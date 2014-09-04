package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import junit.framework.Test;



@Retention(RetentionPolicy.RUNTIME)
public @interface SuiteClasses {


	Class<? extends Test>[] suite() default {};

}
