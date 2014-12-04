package com.mkl.websuites.internal.command.impl.flow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import mockit.VerificationsInOrder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.impl.ParameterizedCommand;
import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatHandler;
import com.mkl.websuites.internal.command.impl.validator.SampleDataProvider;



@RunWith(JUnitParamsRunner.class)
public class RepeatControlFlowHandlerTest {
	
	private RepeatControlFlowHandler sut;

	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();


	
	@Before
	public void init() {
		new MockUp<ParameterizedCommand>() {
			@Mock
			void populateBrowser() {}
		};
	}
	
	
	
	
	@Test
	public void shouldRunCommandNTimes(@Mocked final Command command) throws Exception {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("times", "7");
		sut = new RepeatControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		// when
		sut.run();
		// then
		new Verifications() {{
			command.run();
			times = 7;
		}
		};
	}
	
	
	
	@Test
	public void shouldRunCommandNTimesWithCounter(@Mocked final Command command) throws Exception {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("times", "7");
		params.put("counter", "cnt");
		sut = new RepeatControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		// when
		sut.run();
		// then
		new Verifications() {{
			command.run();
			times = 7;
		}};
		assertThat(WebSuitesUserProperties.get().getNumberProperty("cnt")).isEqualTo(7);
	}
	
	
	
	@Parameters({"times=5;another=2", "time=5", "times=5;counter=i;another=2", "times=5;cnt=i"})
	@Test
	public void shouldNotPassTimesValidationIncorrectAttributes(String encodedProperties) {
		// given
		String[] props = encodedProperties.split(";");
		Map<String, String> params = new HashMap<String, String>();
		for (String prop : props) {
			String[] value = prop.split("=");
			params.put(value[0], value[1]);
		}
		sut = new RepeatControlFlowHandler(params);
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageStartingWith("Given parameters")
			.hasMessageContaining("don't match command allowed parameters");
		//then
		sut.run();
	}
	
	
	@Parameters({"--2", "sfdf", "$erer}"})
	@Test
	public void shouldNotPassValidationTimesValueIncorrect(String timesValue) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("times", timesValue);
		sut = new RepeatControlFlowHandler(params );
		// and
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("must be proper integer value");
		//when
		sut.run();
		//then expect exception
	}
	
	
	
	@Test
	public void shouldNotPassValidationTimesValueNotInRange() {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("times", "-1");
		sut = new RepeatControlFlowHandler(params );
		// and
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Integer value for param")
			.hasMessageContaining("must be between");
		//when
		sut.run();
		//then expect exception
	}

	
	@Test
	public void shouldDoRepeatTimesWithPropertyValue(@Mocked final Command command) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("times", "${howMany}");
		sut = new RepeatControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		// and
		WebSuitesUserProperties.get().setProperty("howMany", "12");
		// when
		sut.run();
		// then
		new Verifications() {{
			command.run();
			times = 12;
		}
		};
	}
	
	
	
	@Parameters({"wef", "O", "-2", "43ffddf", "efe35"})
	@Test
	public void shouldNotPassValidationWithResolvedProperty(String howManyTimes) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("times", "${howMany}");
		sut = new RepeatControlFlowHandler(params);
		// and
		WebSuitesUserProperties.get().setProperty("howMany", howManyTimes);
		// when
		expectedException.expect(WebSuitesException.class);
		// then
		sut.run();
	}
	
	
	
	
	

	
	
	@Parameters({
		"com.mkl.websuites.internal.command.impl.validator.SampleDataProvider, true",
		"com.mkl.websuites.internal.command.impl.validator.NotImplementingDataProvider, false",
		"com.mkl.websuites.internal.command.impl.validator.WithoutNoArgConstructor, false"
	})
	@Test
	public void shouldDoRepeatDataValidation(String dataProvider, boolean isValid) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("dataProvider", dataProvider);
		sut = new RepeatControlFlowHandler(params);
		//when
		if (!isValid) {
			expectedException.expect(WebSuitesException.class);
		}
		//then
		sut.run();
	}
	
	
	@Test
	public void shouldDoRepeatDataProvider(
			@Mocked final Command command, @Mocked final WebSuitesUserProperties mockedProps) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("dataProvider",
				"com.mkl.websuites.internal.command.impl.validator.SampleDataProvider");
		sut = new RepeatControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		// and
		new NonStrictExpectations() {{
			WebSuitesUserProperties.get();
			result = mockedProps;
		}
		};
		//when
		sut.run();
		//then
		final List<Map<String, String>> providedData = new SampleDataProvider().provideData();
		new VerificationsInOrder() {{
			mockedProps.populateFrom(providedData.get(0));
			command.run();
			mockedProps.populateFrom(providedData.get(1));
			command.run();
			mockedProps.populateFrom(providedData.get(2));
			command.run();
		}
		};
	}
	
	
	
	private static boolean flagForHandlerInvocationMarker; // a bit dirty, but I see no mocking here...
	
	public static class CustomRepeatHandler implements RepeatHandler {

		@Override
		public void doRepeat(List<Command> nestedCommands) {
			flagForHandlerInvocationMarker = true;
			assertThat(nestedCommands).hasSize(4);
		}
		
	}
	
	@Test
	public void shouldDoRepeatWithHandler(@Mocked final Command command) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("handler", CustomRepeatHandler.class.getName());
		sut = new RepeatControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command, command, command, command));
		// and
		flagForHandlerInvocationMarker = false;
		//when
		sut.run();
		//then
		assertThat(flagForHandlerInvocationMarker).isTrue();
	}
	
	
	
	@Test
	public void shouldNotPassValidationWithDoRepeatWithHandler() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("handler", "wrong");
		sut = new RepeatControlFlowHandler(params);
		//when
		expectedException.expect(WebSuitesException.class);
		//then
		sut.run();
	}

	
	
	@Test
	public void shouldBeSubtest() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3");
		params.put("subtest", "true");
		sut = new RepeatControlFlowHandler(params);
		sut.run(); // for validation
		//when
		boolean isSubtest = sut.isSubtest();
		//then
		assertThat(isSubtest).isTrue();
	}
	
	
	@Test
	public void shouldNotBeSubtestFalseValue() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3");
		params.put("subtest", "false");
		sut = new RepeatControlFlowHandler(params);
		sut.run(); // for validation
		//when
		boolean isSubtest = sut.isSubtest();
		//then
		assertThat(isSubtest).isFalse();
	}
	
	
	@Test
	public void shouldNotBeSubtestNoAttribute() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3");
		sut = new RepeatControlFlowHandler(params);
		sut.run(); // for validation
		//when
		boolean isSubtest = sut.isSubtest();
		//then
		assertThat(isSubtest).isFalse();
	}

}
