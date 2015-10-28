package com.mkl.websuites.test.integration.command;

import com.mkl.websuites.WebSuitesConfig_rename;
import com.mkl.websuites.test.BrowsersConfig;

@WebSuitesConfig_rename(browsers = {"html"}, browsersConfiguration=BrowsersConfig.class, waitTimeout = 10)
public class LocalConfigForCommandTests {}
