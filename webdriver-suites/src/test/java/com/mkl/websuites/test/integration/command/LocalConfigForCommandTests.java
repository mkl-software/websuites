package com.mkl.websuites.test.integration.command;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.test.BrowsersConfig;

@WebSuitesConfig(browsers = {"html"}, browsersConfiguration=BrowsersConfig.class, waitTimeout = 5)
public class LocalConfigForCommandTests {}
