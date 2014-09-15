package com.mkl.websuites.internal.command;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class BaseCommand implements Command {

	
	protected WebDriver browser;
	
	
	@Override
	public void run() {
		
		browser = ServiceFactory.get(BrowserController.class).getWebDriver();
		
		log.debug("running " + this.getClass().getName() + " command");
		
		runCommand();
	}


	protected abstract void runCommand();
	
	
}
