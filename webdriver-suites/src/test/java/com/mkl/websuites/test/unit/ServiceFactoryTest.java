package com.mkl.websuites.test.unit;

import mockit.Deencapsulation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.Extension;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.services.DefaultServiceDefinitions;
import com.mkl.websuites.internal.services.ServiceDefinition;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;
import com.mkl.websuites.internal.services.ServiceFactory;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceFactoryTest {

	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
	@WebSuites
	public static class MockRunner extends WebSuitesRunner {}
	
	@BeforeClass
	public static void init() {
		WebSuitesConfig.initializeWebsuitesConfig(MockRunner.class);
	}
	
	
	@Test(expected = WebSuitesException.class)
	public void test0_FactoryNotInitialized() {
		
		ServiceFactory.get(BrowserController.class);
	}
	
	
	
	@Test
	public void test1_AllServicesCreation() {
		
		ServiceFactory.init();
		
		Service[] allDefaultServices =
				DefaultServiceDefinitions.class.getAnnotation(ServiceDefinition.class).value(); 

		for (Service service : allDefaultServices) {
			
			Assert.assertEquals(service.implementation(),
					            ServiceFactory.get(service.service()).getClass());
		}
		
	}
	
	
	
	
	@WebSuites(
			extension = @Extension(
					serviceOverrides =
					@Service(service = BrowserController.class,
							 implementation = DummyBrowserControllerWrong.class))
	)
	public static class DummyRunnerClassForWrongOverride extends WebSuitesRunner {}
	
	
	@WebSuites(
			extension = @Extension(
					serviceOverrides =
					@Service(service = BrowserController.class,
					implementation = DummyBrowserControllerCorrect.class))
			)
	public static class DummyRunnerClassForCorrectOverride extends WebSuitesRunner {}
	
	
	
	
	
	public static class DummyBrowserControllerWrong extends StandardBrowserController {
		// no getInstance() method
	}
	
	public static class DummyBrowserControllerCorrect extends StandardBrowserController {
		public static DummyBrowserControllerCorrect getInstance() {
			return new DummyBrowserControllerCorrect();
		}
	}

	
	@Test
	public void test2a_ServiceOverridesIncorrectImplementation() {
		
		Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);
		
		WebSuitesConfig.initializeWebsuitesConfig(DummyRunnerClassForWrongOverride.class);
		ServiceFactory.init();
		
		expectedException
			.expect(WebSuitesException.class)
			.hasMessageContaining("Cannot instantiate")
			.hasMessageContaining("DummyBrowserControllerWrong")
			.hasMessageContaining("getInstance()");
		
		ServiceFactory.get(BrowserController.class);
	}
	
	
	@Test
	public void test2b_ServiceOverridesCorrectImpl() {
		
		Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);
		
		WebSuitesConfig.initializeWebsuitesConfig(DummyRunnerClassForCorrectOverride.class);
		ServiceFactory.init();
		
		BrowserController instance = ServiceFactory.get(BrowserController.class);
		
		Assert.assertEquals(DummyBrowserControllerCorrect.class, instance.getClass());
	}
}
