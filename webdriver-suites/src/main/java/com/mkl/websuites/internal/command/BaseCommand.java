package com.mkl.websuites.internal.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.scenario.SourceLine;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class BaseCommand implements Command, SourceInfoHolder {

	
	protected WebDriver browser;

	private SourceLine sourceLine;
	
	protected static SoftAssertions softly = new SoftAssertions();


	@Override
	public void run() {
		
		browser = ServiceFactory.get(BrowserController.class).getWebDriver();
		
		log.debug("running " + this.getClass().getName() + " command");
		
		try {
			runStandardCommand();
			
		} catch (Throwable e) {
			
			augmentErrorMessageWithCommandSourceFileInfo(e);
			throw e;
		}
			
	}
	
	

	protected void augmentErrorMessageWithCommandSourceFileInfo(Throwable e) {
		try {
			String newMessage = e.getMessage()
					+ "\n"
					+ getCommandSourceLine().printSourceInfo();
			FieldUtils.writeField(e, "detailMessage", newMessage, true);
		} catch (IllegalArgumentException| IllegalAccessException|  SecurityException e1) {
			e1.printStackTrace();
		}
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
	
	
	@Override
	public SourceLine getCommandSourceLine() {
		return sourceLine;
	}
	
	@Override
	public void setCommandSourceLine(SourceLine sourceLine) {
		this.sourceLine = sourceLine;
	}
}
