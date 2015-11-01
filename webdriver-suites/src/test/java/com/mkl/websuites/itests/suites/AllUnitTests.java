package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.WebSuitesRunnerTest;
import com.mkl.websuites.WebSuitesUserPropertiesTest;
import com.mkl.websuites.internal.command.OperationOnWebElementTest;
import com.mkl.websuites.internal.command.StandardCommandBuilderTest;
import com.mkl.websuites.internal.command.StandardCommandParserTest;
import com.mkl.websuites.internal.command.StandardCommandPostProcessorTest;
import com.mkl.websuites.internal.command.StandardCommandTestConverterTest;
import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandlerTest;
import com.mkl.websuites.internal.command.impl.flow.iff.BrowserSetConditionTest;
import com.mkl.websuites.internal.command.impl.flow.iff.IfControlFlowHandlerTest;
import com.mkl.websuites.internal.command.impl.flow.repeat.InlineDataProviderTest;
import com.mkl.websuites.internal.command.impl.validator.BooleanParamValidatorTest;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidatorTest;
import com.mkl.websuites.internal.command.impl.validator.DataProviderParamValidatorTest;
import com.mkl.websuites.internal.scenario.StandardScenarioFilePreprocessorTest;
import com.mkl.websuites.internal.services.ServiceFactoryTest;
import com.mkl.websuites.internal.tests.MultiBrowserTestCaseTest;
import com.mkl.websuites.internal.tests.ScenarioFolderTestTest;


/**
 * For IDE runner, Maven will gather all unit tests automatically...
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses(
		{
			WebSuitesRunnerTest.class,
			OperationOnWebElementTest.class,
			StandardCommandBuilderTest.class,
			StandardCommandParserTest.class,
			StandardCommandPostProcessorTest.class,
			StandardCommandTestConverterTest.class,
			DataProviderParamValidatorTest.class,
			RepeatControlFlowHandlerTest.class,
			InlineDataProviderTest.class,
			IfControlFlowHandlerTest.class,
			BrowserSetConditionTest.class,
			BooleanParamValidatorTest.class,
			CommandSchemaValidatorTest.class,
			StandardScenarioFilePreprocessorTest.class,
			ServiceFactoryTest.class,
			MultiBrowserTestCaseTest.class,
			ScenarioFolderTestTest.class,
			WebSuitesUserPropertiesTest.class
		}
)
public class AllUnitTests {

}
