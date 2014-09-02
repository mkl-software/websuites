package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface SuiteClasses {


	Class<? extends MultiBrowserSuite>[] suites() default {};

	Class<? extends MultiBrowserTestCase>[] tests()  default {};

}
