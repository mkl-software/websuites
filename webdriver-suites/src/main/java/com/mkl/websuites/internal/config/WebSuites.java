package com.mkl.websuites.internal.config;

import com.mkl.websuites.Browser;

public @interface WebSuites {

	String[] browsers() default {};
	
	String browsersProperty() default "";
	
	Folder[] folders() default {};
	
	ScenarioFile[] scenarios() default {};
	
	TestClass[] classes() default {};
	
	String properies() default "";
	
	SiteConfig site() default @SiteConfig;
	
	Browser[] browserConfiguration() default {};
	
	Extension extension() default @Extension;
	
}
