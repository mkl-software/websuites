package com.mkl.websuites.test.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.test.integration.command.CheckCommandTest;
import com.mkl.websuites.test.integration.command.CheckTitleTest;
import com.mkl.websuites.test.integration.command.EmptyCommandIntegrationTest;
import com.mkl.websuites.test.integration.command.GotoCommandTest;
import com.mkl.websuites.test.integration.command.RepeatTimesCommandTest;
import com.mkl.websuites.test.integration.command.TypeCommandTest;



@RunWith(Suite.class)
@SuiteClasses({
	EmptyCommandIntegrationTest.class,
	GotoCommandTest.class,
	GotoCommandTest.class,
	TypeCommandTest.class,
	RepeatTimesCommandTest.class,
	CheckCommandTest.class,
	CheckTitleTest.class
})
public class AllCommandTests {}
