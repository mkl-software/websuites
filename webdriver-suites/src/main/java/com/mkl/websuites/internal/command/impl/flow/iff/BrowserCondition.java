package com.mkl.websuites.internal.command.impl.flow.iff;

import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceFactory;

public class BrowserCondition implements IfCondition {

	
	private String requiredBrowser;
	
	private boolean negate;
	
	
	
	
	public BrowserCondition(String requiredBrowser) {
		this(requiredBrowser, false);
	}
	
	
	public BrowserCondition(String requiredBrowser, boolean reverseCondition) {
		this.requiredBrowser = requiredBrowser;
		this.negate = reverseCondition;
	}




	@Override
	public boolean isConditionMet() {
		
		String curentBrowser = ServiceFactory.get(BrowserController.class).currentBrowser();
		
		boolean browsersOK = requiredBrowser.equals(curentBrowser);
		
		if (negate) {
			return !browsersOK;
		}
		
		return browsersOK;
	}

}
