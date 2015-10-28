package com.mkl.websuites.internal.config;

import junit.framework.Test;

public @interface TestClass {

	
	Class<? extends Test> value();
}
