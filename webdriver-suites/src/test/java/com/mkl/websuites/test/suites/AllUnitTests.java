package com.mkl.websuites.test.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mkl.websuites.internal.command.impl.OperationOnWebElementTest;
import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandlerTest;
import com.mkl.websuites.internal.command.impl.validator.DataProviderParamValidatorTest;
import com.mkl.websuites.test.unit.core.CommandSchemaValidatorTest;



@RunWith(Suite.class)
@SuiteClasses(
		{
			CommandSchemaValidatorTest.class,
			OperationOnWebElementTest.class,
			DataProviderParamValidatorTest.class,
			RepeatControlFlowHandlerTest.class
		}
)
public class AllUnitTests {

}
