package com.mkl.websuites.internal.config;

import com.mkl.websuites.internal.MultiBrowserTestCase;


@WebSuites(
		browsers = "ff",
		folders = @Folder(path = "/somepath"),
		scenarios = @ScenarioFile("file.scn"),
		classes = @TestClass(MultiBrowserTestCase.class),
		site = @SiteConfig(host = "myhost.com"),
		extension = @Extension(commandExtensionPackages = "com.mkl.mycommands"),
		properies = "init.properties"
)
public class Temp {

}
