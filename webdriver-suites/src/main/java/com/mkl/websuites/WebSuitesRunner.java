package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuitesRunner {

	Class<?> configurationClass();

	Class<? extends MultiBrowserSuite>[] suite();


}
