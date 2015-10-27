package com.mkl.websuites.test.integration.command;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.test.BrowsersConfig;

@WebSuitesConfig(browsers = {"ff"}, browsersConfiguration=BrowsersConfig.class, waitTimeout = 10)
public class LocalConfigForCommandTests {}
