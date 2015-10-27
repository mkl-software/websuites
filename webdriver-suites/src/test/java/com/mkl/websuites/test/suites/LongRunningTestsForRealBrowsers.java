package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.integration.command.AllCheckCommandsFailingTest;
import com.mkl.websuites.test.integration.command.AllCheckCommandsPassingTest;
import com.mkl.websuites.test.integration.command.CheckCommandTest;
import com.mkl.websuites.test.integration.command.ClickCommandTest;
import com.mkl.websuites.test.integration.command.TypeCommandTest;


@RunWith(Suite.class)
@SuiteClasses({
	AllCheckCommandsPassingTest.class,
	AllCheckCommandsFailingTest.class,
	TypeCommandTest.class,
	ClickCommandTest.class,
	CheckCommandTest.class,
})
public class LongRunningTestsForRealBrowsers {

}
