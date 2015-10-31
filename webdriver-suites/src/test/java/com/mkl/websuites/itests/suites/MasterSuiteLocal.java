package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.itests.nonweb.flow.IfDetailedIntegrationTest;
import com.mkl.websuites.itests.nonweb.flow.RepeatDetailedIntegrationTest;


/**
 * Should work also in HTMLUnit for quick web tests.
 * If run with "-DtestBrowser=ff" will also run for
 * the real browser.
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
		AllUnitTests.class,
		CommandQuickTests.class,
		RepeatDetailedIntegrationTest.class,
		IfDetailedIntegrationTest.class
})
public class MasterSuiteLocal {

}
