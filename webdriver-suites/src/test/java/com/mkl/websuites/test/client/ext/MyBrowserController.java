package com.mkl.websuites.test.client.ext;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.impl.BrowserControllerImpl;


@Slf4j
public class MyBrowserController extends BrowserControllerImpl {

	private static MyBrowserController instance = new MyBrowserController();

	public static MyBrowserController getInstance() {
		return instance ;
	}
	
	
	@Override
	public String getBrowserDisplayName(String currentBrowser) {
		log.debug("custom getDisplayName()");
		return "{custo} " + super.getBrowserDisplayName(currentBrowser);
	}
	
}
