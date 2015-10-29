package com.mkl.websuites.test;

import com.mkl.websuites.config.BrowserConifg;
import com.mkl.websuites.config.BrowsersDefinition;
import com.mkl.websuites.config.BrowserConifg.BrowserType;


@BrowsersDefinition(browsers = {
		@BrowserConifg(
				browserType = BrowserType.INTERNET_EXPLORER,
				id = "ie",
				displayName = "Internet Explorer",
				webDriverPath = "src/test/resources/drivers/IEDriverServer.exe"
		),
		@BrowserConifg(
				browserType = BrowserType.CHROME,
				id = "chrome",
				displayName = "Chrome",
				webDriverPath = "src/test/resources/drivers/chromedriver.exe"
		)
})
public class BrowsersConfig {

}
