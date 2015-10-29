package com.mkl.websuites.internal.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.BrowserConifg;


@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuites {

	String[] browsers() default {};
	
	String browsersProperty() default "";
	
	Folder[] folders() default {};
	
	ScenarioFile[] scenarios() default {};
	
	TestClass[] classes() default {};
	
	String properies() default "";
	
	SiteConfig site() default @SiteConfig;
	
	BrowserConifg[] browserConfiguration() default {};
	
	Class<?> browserResusableConfiguration() default Object.class;
	
	Extension extension() default @Extension;
	
}
