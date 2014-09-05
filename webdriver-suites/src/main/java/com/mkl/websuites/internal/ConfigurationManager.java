package com.mkl.websuites.internal;

import com.mkl.websuites.WebSuitesConfig;

public interface ConfigurationManager {

	void setConfiguration(WebSuitesConfig configuration);

	WebSuitesConfig getConfiguration();

	
}
