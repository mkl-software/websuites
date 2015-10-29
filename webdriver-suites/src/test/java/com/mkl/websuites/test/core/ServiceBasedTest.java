package com.mkl.websuites.test.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.BeforeClass;

import com.mkl.websuites.internal.services.ServiceFactory;



@Slf4j
public abstract class ServiceBasedTest<T> {

	@BeforeClass
	public static void init() {
		
		try {
			ServiceFactory.init();
		} catch (Exception e) {
			log.debug("ServiceFactory already initialized, ignoring, not a problem");
		}
	}
	
	protected T logic() {
		return ServiceFactory.get(getServiceUnderTestClass());
	}
	

	@Before
	public void serviceExisting() {
		try {
			assertNotNull(ServiceFactory.get(getServiceUnderTestClass()));
		} catch (NullPointerException e) {
			fail("service not configured");
		}
	}

	protected abstract Class<T> getServiceUnderTestClass();

}
