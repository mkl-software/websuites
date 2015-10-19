package com.mkl.websuites.internal.command.impl.check;

import java.util.regex.Pattern;

import org.assertj.core.api.SoftAssertions;

public class CheckUtils {

	public static void softFail(SoftAssertions softAssertion, String message) {
		
		// no "fail(msg)" method in soft assertions :/ Workaround:
		softAssertion.assertThat(false)
			.overridingErrorMessage(message)
			.isTrue();
	}
	
	
	public static Pattern patternOf(String text) {
		
		return Pattern.compile(text, Pattern.DOTALL);
	}

}
