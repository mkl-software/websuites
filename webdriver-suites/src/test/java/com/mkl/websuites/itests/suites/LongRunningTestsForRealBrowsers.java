package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.itests.web.command.AllCheckCommandsFailingTest;
import com.mkl.websuites.itests.web.command.AllCheckCommandsPassingTest;
import com.mkl.websuites.itests.web.command.CheckCommandTest;
import com.mkl.websuites.itests.web.command.TypeCommandTest;


@RunWith(Suite.class)
@SuiteClasses({
	AllCheckCommandsPassingTest.class,
	AllCheckCommandsFailingTest.class,
	TypeCommandTest.class,
	CheckCommandTest.class,
})
public class LongRunningTestsForRealBrowsers {

}
