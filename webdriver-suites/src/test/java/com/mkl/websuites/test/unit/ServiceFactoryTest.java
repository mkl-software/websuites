package com.mkl.websuites.test.unit;

import mockit.Deencapsulation;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.ext.Customization;
import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.impl.BrowserControllerImpl;
import com.mkl.websuites.internal.services.DefaultServiceDefinitions;
import com.mkl.websuites.internal.services.ServiceDefinition;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceFactoryTest {

	
	
	
	@Test(expected = WebSuitesException.class)
	public void test0_FactoryNotInitialized() {
		
		ServiceFactory.get(BrowserController.class);
	}
	
	
	
	@Test
	public void test1_AllServicesCreation() {
		
		ServiceFactory.init(null);
		
		Service[] allDefaultServices =
				DefaultServiceDefinitions.class.getAnnotation(ServiceDefinition.class).value(); 

		for (Service service : allDefaultServices) {
			
			Assert.assertEquals(service.implementation(),
					            ServiceFactory.get(service.service()).getClass());
		}
		
	}
	
	
	
	@Customization(serviceOverrides = {
		@Service(service = BrowserController.class, implementation = DummyBrowserControllerWrong.class)
	})
	public static class LocalCustomizationTest2 {}
	
	
	@WebSuitesConfig(
		basePath = "", browsers = {}, host = "",
		serviceOverrides = LocalCustomizationTest2.class
	)
	public static class LocalConfigTest2 {}
	
	@WebSuitesRunner(
		configurationClass = LocalConfigTest2.class,
		suite = {}
	)
	public static class DummyRunnerClass {}
	
	public static class DummyBrowserControllerWrong extends BrowserControllerImpl {
		// no getInstance() method
	}
	
	public static class DummyBrowserControllerCorrect extends BrowserControllerImpl {
		public static DummyBrowserControllerCorrect getInstance() {
			return new DummyBrowserControllerCorrect();
		}
	}

	
	@Test(expected = WebSuitesException.class)
	public void test2a_ServiceOverridesIncorrectImplementation() {
		
		Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);
		
		ServiceFactory.init(DummyRunnerClass.class);
		
		ServiceFactory.get(BrowserController.class);
	}
	
	
	@Test(expected = WebSuitesException.class)
	public void test2b_ServiceOverridesCorrectImpl() {
		
		Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);
		
		ServiceFactory.init(DummyRunnerClass.class);
		
		BrowserController instance = ServiceFactory.get(BrowserController.class);
		
		Assert.assertEquals(DummyBrowserControllerCorrect.class, instance.getClass());
	}
}
