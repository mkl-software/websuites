package com.mkl.websuites.test.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.BeforeClass;

import com.mkl.websuites.internal.services.ServiceFactory;



@Slf4j
public abstract class ServiceBasedTest {

	@BeforeClass
	public static void init() {
		
		try {
			ServiceFactory.init(null);
		} catch (Exception e) {
			log.debug("ServiceFactory already initialized, ignoring, not a problem");
		}
	}

	@Before
	public void serviceExisting() {
		try {
			assertNotNull(ServiceFactory.get(getServiceUnderTest()));
		} catch (NullPointerException e) {
			fail("service not configured");
		}
	}

	protected abstract Class<?> getServiceUnderTest();

}
