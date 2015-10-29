package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.config.BrowserConifg;
import com.mkl.websuites.config.Extension;
import com.mkl.websuites.config.Folder;
import com.mkl.websuites.config.ScenarioFile;
import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.config.TestClass;


@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuites {

	String[] browsers() default {};
	
	String browsersProperty() default "";
	
	Folder[] folders() default {};
	
	ScenarioFile[] scenarios() default {};
	
	TestClass[] classes() default {};
	
	String properties() default "";
	
	SiteConfig site() default @SiteConfig;
	
	BrowserConifg[] browserConfiguration() default {};
	
	Class<?> browserResusableConfiguration() default Object.class;
	
	Extension extension() default @Extension;
	
}
