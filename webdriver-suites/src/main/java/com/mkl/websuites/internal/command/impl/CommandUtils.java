package com.mkl.websuites.internal.command.impl;

import java.util.regex.Pattern;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;

public class CommandUtils {

	public static void softFail(SoftAssertions softAssertion, String message) {
		
		// no "fail(msg)" method in soft assertions :/ Workaround:
		softAssertion.assertThat(false)
			.overridingErrorMessage(message)
			.isTrue();
	}
	
	
	public static boolean checkIfElementIsSelect(WebElement element) {
		return element.getTagName().equalsIgnoreCase("select");
	}
	
	
	public static Pattern patternOf(String text) {
		
		return Pattern.compile(text, Pattern.DOTALL);
	}

}
