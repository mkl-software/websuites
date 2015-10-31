package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



/**
 * Complete test case suite.
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
			MasterSuiteLocal.class,
			LongRunningTestsForRealBrowsers.class
		}
)
public class MasterSuite {

}
