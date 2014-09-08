package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.integration.simplest.onebrowser.SimplestSingleTestOneBrowser;
import com.mkl.websuites.test.integration.simplest.twobrowser.SimplestSingleTestTwoBrowsers;



@RunWith(Suite.class)
@SuiteClasses(
		{
			SimplestSingleTestOneBrowser.class,
			SimplestSingleTestTwoBrowsers.class,
		}
)
public class WebTests {

}
