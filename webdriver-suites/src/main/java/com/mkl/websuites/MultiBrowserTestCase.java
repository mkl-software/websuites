package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {

	
	private  BrowserController browserController = ServiceFactory.get(BrowserController.class);

	protected WebSuitesConfig configuration = ServiceFactory.get(ConfigurationManager.class).getConfiguration();
	
	protected String currentBrowser;
	
	protected WebDriver webDriver;
	
	protected String basePath;
	

	
	public MultiBrowserTestCase() {
		super();
		this.basePath = configuration.host() + ":" + configuration.port() + configuration.basePath();
		this.currentBrowser = browserController.getLocalBrowserNameForTestInit();
	}
	
	
	@Override
	public String getName() {
		return getTestName() + " [" + currentBrowser + "]";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		log.debug("running: " + this.getClass().getName() + " with test name: " + getName());
		
		this.webDriver = browserController.getWebDriver();
		
		runLocally();
	}
	
	protected abstract void runLocally();


	protected abstract String getTestName();

}
