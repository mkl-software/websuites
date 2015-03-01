package com.mkl.websuites.internal.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class BaseCommand implements Command {

	
	protected WebDriver browser;
	
	


	@Override
	public void run() {
		
		browser = ServiceFactory.get(BrowserController.class).getWebDriver();
		
		log.debug("running " + this.getClass().getName() + " command");
		
		runStandardCommand();
			
	}
	
	

	protected abstract void runStandardCommand();



	protected String populateStringWithProperties(String origValue) {
		String populated = new String(origValue);
		String regex = "\\$\\{(.*?)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(origValue);
		while (matcher.find()) {
			String propName = matcher.group(1);
			String value = WebSuitesUserProperties.get().getProperty(propName);
			if (value != null) {
				populated = populated.replaceAll("\\$\\{" + propName +"\\}", value);
			}
		}
		return populated;
	}
	
	
}
