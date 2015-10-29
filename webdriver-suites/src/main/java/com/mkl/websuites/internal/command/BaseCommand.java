package com.mkl.websuites.internal.command;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

import com.mkl.websuites.internal.CommonUtils;
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

		return CommonUtils.populateStringWithProperties(origValue);
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
