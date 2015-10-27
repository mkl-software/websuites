package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Should work also in HTMLUnit for quick web tests.
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
			NonWebTests.class,
			WebTests.class,
		}
)
public class MasterSuiteLocal {

}
