package com.mkl.websuites.test.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.impl.check.CheckTextPresentCommand;
import com.mkl.websuites.internal.tests.MultiBrowserTestCase;

public class SampleStandaloneTest extends MultiBrowserTestCase {


	@Override
	protected void runLocally() {
		browser.get("http://google.com");
		WebElement queryBox = browser.findElement(By.id("lst-ib"));
		queryBox.sendKeys("selenium\n");
		new CheckTextPresentCommand("Selenium - Web Browser Automation").run();
	}

	@Override
	protected String getTestName() {
		return this.getClass().getName();
	}

	

}
