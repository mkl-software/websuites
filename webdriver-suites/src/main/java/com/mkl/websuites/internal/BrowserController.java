package com.mkl.websuites.internal;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.WebSuitesConfig;

public interface BrowserController {

	public abstract void initializeBrowsersEnvironment(WebSuitesConfig config);

	public abstract void addBrowser(String browser);

	public abstract String currentBrowser();

	public abstract String removeCurrentBrowser();

	public abstract WebDriver getWebDriver();

	public abstract void setNextWebDriver();

	public abstract String getLocalBrowserNameForTestInit();

	public abstract String getBrowserDisplayName(String currentBrowser);

}