package com.mkl.websuites.internal.config;


@FrameworkInternalConfig(
		
	baseCommandScanPath = "com.mkl.websuites.internal.command.impl"
	
)
public class FrameworkConfiguration {

	
	public static FrameworkInternalConfig get() {
		return FrameworkConfiguration.class.getAnnotation(FrameworkInternalConfig.class);
	}
}
