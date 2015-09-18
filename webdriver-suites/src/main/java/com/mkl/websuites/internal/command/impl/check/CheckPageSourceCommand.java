package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;

@CommandDescriptor(name = "checkPageSource", argumentTypes = String.class)
public class CheckPageSourceCommand extends AbstractSingleStringCheck {

	protected String pageSource;
	
	public CheckPageSourceCommand(String pageSource) {
		this.pageSource = pageSource;
	}

	
	@Override
	protected String getStringParam() {
		return browser.getPageSource();
	}

	@Override
	protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {
		
		assertThatUrl
			.overridingErrorMessage("Page source expected to be exactly '%s', but the actual page source was '%s'",
					pageSource, currentPageSource)
			.isEqualTo(pageSource);
	}


}
