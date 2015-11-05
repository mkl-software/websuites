package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.itests.web.command.CheckTitleTest;
import com.mkl.websuites.itests.web.command.EmptyCommandIntegrationTest;
import com.mkl.websuites.itests.web.command.GotoCommandTest;
import com.mkl.websuites.itests.web.command.RepeatTimesCommandTest;
import com.mkl.websuites.itests.web.command.SelectCheckboxTest;
import com.mkl.websuites.itests.web.command.SelectCommandsTest;
import com.mkl.websuites.itests.web.command.SourceInfoTest;


/**
 * Command tests that can be run with HTMLUnit browser to quickly test in IDe.
 * 
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses({EmptyCommandIntegrationTest.class, SourceInfoTest.class, GotoCommandTest.class,
        RepeatTimesCommandTest.class, CheckTitleTest.class, SelectCommandsTest.class, SelectCheckboxTest.class})
public class CommandQuickTests {
}
