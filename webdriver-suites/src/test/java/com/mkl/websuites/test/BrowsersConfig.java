package com.mkl.websuites.test;

import com.mkl.websuites.Browser;
import com.mkl.websuites.BrowsersConfiguration;


@BrowsersConfiguration(browsers = {
		@Browser(
				id = "ie",
				displayName = "Internet Explorer",
				webDriverPath = ""
		),
		@Browser(
				id = "chrome",
				displayName = "Chrome",
				webDriverPath = ""
		),
		@Browser(
				id = "chrome",
				displayName = "Chrome",
				webDriverPath = ""
			)
})
public class BrowsersConfig {

}
