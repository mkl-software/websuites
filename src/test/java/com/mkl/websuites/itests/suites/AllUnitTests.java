/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mkl.websuites.itests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.MultiBrowserTestCaseTest;
import com.mkl.websuites.WebSuitesRunnerTest;
import com.mkl.websuites.WebSuitesUserPropertiesTest;
import com.mkl.websuites.command.OperationOnWebElementTest;
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
import com.mkl.websuites.internal.tests.ScenarioFolderTestTest;


/**
 * For IDE runner, Maven will gather all unit tests automatically...
 * 
 * @author klosinskim
 *
 */
@RunWith(Suite.class)
@SuiteClasses({WebSuitesRunnerTest.class, OperationOnWebElementTest.class, StandardCommandBuilderTest.class,
        StandardCommandParserTest.class, StandardCommandPostProcessorTest.class,
        StandardCommandTestConverterTest.class, DataProviderParamValidatorTest.class,
        RepeatControlFlowHandlerTest.class, InlineDataProviderTest.class, IfControlFlowHandlerTest.class,
        BrowserSetConditionTest.class, BooleanParamValidatorTest.class, CommandSchemaValidatorTest.class,
        StandardScenarioFilePreprocessorTest.class, ServiceFactoryTest.class, MultiBrowserTestCaseTest.class,
        ScenarioFolderTestTest.class, WebSuitesUserPropertiesTest.class})
public class AllUnitTests {

}
