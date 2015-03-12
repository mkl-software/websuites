package com.mkl.websuites.test.unit.scenario.flows;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.test.unit.scenario.CommandInvocationVerifier;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.ScenarioFiles;

public class RepeatDetailedIntegrationTest {

	
	private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();


	@ScenarioFiles("src/test/resources/integration/non-web/repeat/01.scn")
	public static class ScenarioFile extends ScenarioFileTest {}
	
	@WebSuitesConfig(browsers = "none")
	public static class LocalConfig {}
	
	@WebSuitesRunner(suite = ScenarioFile.class, configurationClass = LocalConfig.class)
	public static class Runner extends WebSuites {}
	
	
	@Test
	public void shouldRun00SimplestCase() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/00.scn");
		
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
	public void shouldRun01Repeat7Times() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/01.scn");
		
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
	public void shouldRun02Repeat5TimesWithDefaultCounter() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/02.scn");
		
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
	public void shouldRun03Repeat5TimesWithNamedCounter() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/03.scn");
		
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
	public void shouldRun04RepeatWithInlineDataOneParam() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/04.scn");
		
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
	public void shouldRun05RepeatWithInlineDataThreeParams() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/05.scn");
		
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
	public void shouldRun06RepeatWithInlineDataNamesParams() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation("src/test/resources/integration/non-web/repeat/06.scn");
		
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
	
	
	
	
	
	
	private void overrideScenarioFileNameAnnotation(final String name) throws Throwable {
		
		final ScenarioFiles origScnFiles = ScenarioFile.class.getAnnotation(ScenarioFiles.class);
		
		Field field = Class.class.getDeclaredField("annotations");
	    field.setAccessible(true);
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		Map<Class<? extends Annotation>, Annotation> annotations = (Map) field.get(ScenarioFile.class);
		
		Annotation newValue = new ScenarioFiles() {
			
			
			@Override
			public String[] value() {
				return new String[] {name};
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return origScnFiles.annotationType();
			}
		};
		
		annotations.put(ScenarioFiles.class, newValue);
	}
	
	
	protected void checkIfNoFailures(Result result) {
		if (result.getFailureCount() > 0) {
			
			System.out.println(result.getFailures());
			
			StringBuffer sb = new StringBuffer();
			
			for (Failure failure : result.getFailures()) {
				sb.append("[" + failure.getTestHeader() + "] ")
					.append(failure.getMessage());
					
			}
			
			fail("There are failurs in the unerlying test being invoked, see "
					+ "details below:\n" + sb.toString());
		}
	}
}
