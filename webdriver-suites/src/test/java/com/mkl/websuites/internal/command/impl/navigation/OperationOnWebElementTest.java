package com.mkl.websuites.internal.command.impl.navigation;


import java.util.HashMap;
import java.util.Map;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications; 

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.impl.ParameterizedCommand;

import com.mkl.websuites.internal.command.impl.navigation.OperationOnWebElement;


public class OperationOnWebElementTest {

	
	private OperationOnWebElement sut;
	
	
	@Before
	public void init() {
		new MockUp<ParameterizedCommand>() {
			@Mock
			void populateBrowser() {}
		};
	}
	
	@Test
	public void shouldRunForCss(@Mocked final WebDriver browser) {
		runFor(browser, "css", ".something", By.cssSelector(".something"));
	}
	
	
	@Test
	public void shouldRunForId(@Mocked final WebDriver browser) {
		runFor(browser, "id", "someId", By.id("someId"));
	}
	
	
	@Test
	public void shouldRunForXpath(@Mocked final WebDriver browser) {
		runFor(browser, "xpath", "//node", By.xpath("//node"));
	}
	
	
	@Test
	public void shouldRunForClassName(@Mocked final WebDriver browser) {
		runFor(browser, "className", "someClass", By.className("someClass"));
	}
	
	
	@Test
	public void shouldRunForName(@Mocked final WebDriver browser) {
		runFor(browser, "name", "someName", By.name("someName"));
	}
	
	
	@Test
	public void shouldRunForTagName(@Mocked final WebDriver browser) {
		runFor(browser, "tagName", "someTagName", By.tagName("someTagName"));
	}
	
	
	@Test
	public void shouldRunForLinkText(@Mocked final WebDriver browser) {
		runFor(browser, "linkText", "someLinkText", By.linkText("someLinkText"));
	}
	
	
	@Test
	public void shouldRunForPartialLinkText(@Mocked final WebDriver browser) {
		runFor(browser, "partialLinkText", "someParialLinkText",
				By.partialLinkText("someParialLinkText"));
	}

	
	
	
	
	private void runFor(final WebDriver browser, String selector, String selectorValue, 
			final By webDriverSelector) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put(selector, selectorValue);
		sut = new OperationOnWebElement(params) {
			@Override
			protected void doOperationOnElement(WebElement elem) {}
		};
		// and
		Deencapsulation.setField(sut, "browser", browser);
		//when
		sut.run();
		//then
		new Verifications() {{
			browser.findElement(webDriverSelector);
		}
		};
	}
}
