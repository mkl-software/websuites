package com.mkl.websuites.internal.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface FrameworkInternalConfig {

	String baseCommandScanPath ();

}
