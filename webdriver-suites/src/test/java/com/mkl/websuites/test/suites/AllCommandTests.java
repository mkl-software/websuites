package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.integration.command.CheckTitleTest;
import com.mkl.websuites.test.integration.command.EmptyCommandIntegrationTest;
import com.mkl.websuites.test.integration.command.GotoCommandTest;
import com.mkl.websuites.test.integration.command.RepeatTimesCommandTest;
import com.mkl.websuites.test.integration.command.SelectCommandsTest;



@RunWith(Suite.class)
@SuiteClasses({
	EmptyCommandIntegrationTest.class,
	GotoCommandTest.class,
	RepeatTimesCommandTest.class,
	CheckTitleTest.class,
	SelectCommandsTest.class
})
public class AllCommandTests {}
