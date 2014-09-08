package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses(
		{
			NonWebTests.class,
			WebTests.class,
		}
)
public class MasterSuite {

}
