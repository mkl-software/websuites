package com.mkl.websuites.test.unit.scenario.flows;

import static com.mkl.websuites.test.core.TestUtils.checkIfNoFailures;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.test.core.TestUtils;
import com.mkl.websuites.test.unit.scenario.CommandInvocationVerifier;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;

public class RepeatDetailedIntegrationTest {

	
	private static final String SCN_DIR = "src/test/resources/integration/non-web/repeat/";
	private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();


	@Scenarios("src/test/resources/integration/non-web/repeat/11.scn")
	public static class ScenarioFile extends ScenarioFileTest {}
	
	@WebSuitesConfig(browsers = "none")
	public static class LocalConfig {}
	
	@WebSuitesRunner(suite = ScenarioFile.class, configurationClass = LocalConfig.class)
	public static class Runner extends WebSuites {}
	
	
	@Test
	public void shouldPassSimplestCaseScn00() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "00.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("the simplest case");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	@Test
	public void shouldRepeat7TimesScn01() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "01.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeated 7 times");
		commandVerifier.expectInvocations("repeated 7 times");
		commandVerifier.expectInvocations("repeated 7 times");
		commandVerifier.expectInvocations("repeated 7 times");
		commandVerifier.expectInvocations("repeated 7 times");
		commandVerifier.expectInvocations("repeated 7 times");
		commandVerifier.expectInvocations("repeated 7 times");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRepeat5TimesWithDefaultCounterScn02() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "02.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeated param value is 1");
		commandVerifier.expectInvocations("repeated param value is 2");
		commandVerifier.expectInvocations("repeated param value is 3");
		commandVerifier.expectInvocations("repeated param value is 4");
		commandVerifier.expectInvocations("repeated param value is 5");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	@Test
	public void shouldRepeat5TimesWithNamedCounterScn03() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "03.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeated param value is 1");
		commandVerifier.expectInvocations("repeated param value is 2");
		commandVerifier.expectInvocations("repeated param value is 3");
		commandVerifier.expectInvocations("repeated param value is 4");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	
	@Test
	public void shouldRepeatWithInlineDataOneParamScn04() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "04.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeated param value is one");
		commandVerifier.expectInvocations("repeated param value is two");
		commandVerifier.expectInvocations("repeated param value is three");
		commandVerifier.expectInvocations("repeated param value is four");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	
	@Test
	public void shouldRepeatWithInlineDataThreeParamsScn05() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "05.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeated param value is one and ein and un");
		commandVerifier.expectInvocations("repeated param value is two and zwei and deux");
		commandVerifier.expectInvocations("repeated param value is three and drei and trois");
		commandVerifier.expectInvocations("repeated param value is four and vier and quatre");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	
	@Test
	public void shouldRepeatWithInlineDataNamesParamsScn06() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "06.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeated param value is p");
		commandVerifier.expectInvocations("repeated param value is q");
		commandVerifier.expectInvocations("repeated param value is r");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(1);
		
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRunRepeatInSubtestRepeatScn07() throws Throwable {
		
		//given
		// bug with commands before subtest repeat are not executed
		overrideScenarioFileNameAnnotation(SCN_DIR + "07.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeat in subtest 1");
		commandVerifier.expectInvocations("repeat in subtest 2");
		commandVerifier.expectInvocations("repeat in subtest 3");
		commandVerifier.expectInvocations("repeat in subtest 4");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(4); // 4 param cases
		
		commandVerifier.checkRemaining();
	}
	
	
	
	@Test
	public void shouldRunCommandsBeforeSubtestRepeatScn08() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "08.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("before repeat");
		commandVerifier.expectInvocations("repeat statement");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(2); // 1 for command before + one param case
		
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRunCommandsAfterSubtestRepeatScn09() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "09.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("repeat statement");
		commandVerifier.expectInvocations("repeat statement");
		commandVerifier.expectInvocations("after repeat");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(3); // 2 param case + 1 for command after
		
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRunCommandsBeforeAndAfterSubtestRepeatScn10() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "10.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("before repeat");
		commandVerifier.expectInvocations("repeat statement");
		commandVerifier.expectInvocations("repeat statement");
		commandVerifier.expectInvocations("repeat statement");
		commandVerifier.expectInvocations("after repeat");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(5); // 1 for command before + 3 param case + 1 command after
		
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRun4ConsecutiveSubtestRepeatsScn11() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "11.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("first repeat statement");
		commandVerifier.expectInvocations("second repeat statement");
		commandVerifier.expectInvocations("third repeat statement");
		commandVerifier.expectInvocations("fourth repeat statement");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(4); // 4 param test cases
		
		commandVerifier.checkRemaining();
	}
	
	
	
	@Test
	public void shouldRunRepeatsWithCommandsCombinationScn12() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "12.scn");
		
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("start test");
		commandVerifier.expectInvocations("first repeat statement 1");
		commandVerifier.expectInvocations("first repeat statement 2");
		commandVerifier.expectInvocations("after first repeat");
		commandVerifier.expectInvocations("something");
		commandVerifier.expectInvocations("something");
		commandVerifier.expectInvocations("something");
		commandVerifier.expectInvocations("second repeat statement a");
		commandVerifier.expectInvocations("second repeat statement b");
		commandVerifier.expectInvocations("second repeat statement c");
		commandVerifier.expectInvocations("second repeat statement d");
		commandVerifier.expectInvocations("after second repeat");
		commandVerifier.expectInvocations("third repeat statement x");
		commandVerifier.expectInvocations("third repeat statement y");
		commandVerifier.expectInvocations("third repeat statement z");
		commandVerifier.expectInvocations("after third repeat");
		commandVerifier.expectInvocations("end of test");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		checkIfNoFailures(result);
		
		assertThat(result.getRunCount()).isEqualTo(13); 
		
		commandVerifier.checkRemaining();
	}
	
	
	
	private void overrideScenarioFileNameAnnotation(final String scenarioName) throws Throwable {
		
		Annotation newValue = new Scenarios() {
			
			
			@Override
			public String[] value() {
				return new String[] {scenarioName};
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return ((Scenarios) ScenarioFile.class.getAnnotation(Scenarios.class)).annotationType();
			}
		};
		
		TestUtils.overrideScenarioFileNameAnnotation(ScenarioFile.class, Scenarios.class, newValue);
	}
	
	
	
	
}
