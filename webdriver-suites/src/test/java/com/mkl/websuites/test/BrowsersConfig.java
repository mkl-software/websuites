package com.mkl.websuites.test;

import com.mkl.websuites.Browser;
import com.mkl.websuites.Browser.BrowserType;
import com.mkl.websuites.BrowsersConfiguration;


@BrowsersConfiguration(browsers = {
		@Browser(
				browserType = BrowserType.INTERNET_EXPLORER,
				localId = "ie",
				displayName = "Internet Explorer",
				webDriverPath = "src/test/resources/drivers/IEDriverServer.exe"
		),
		@Browser(
				browserType = BrowserType.CHROME,
				localId = "chrome",
				displayName = "Chrome",
				webDriverPath = "src/test/resources/drivers/chromedriver.exe"
		)
})
public class BrowsersConfig {

}
