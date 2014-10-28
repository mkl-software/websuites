package com.mkl.websuites.internal.command.impl.flow;

import static org.assertj.core.api.Assertions.*;
import static junitparams.JUnitParamsRunner.$;

import java.util.Arrays;
import java.util.HashMap;
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
	
	
	
	@Test
	public void shouldRepeatWithInlineDataSimpleVersion(@Mocked final Command command) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3;4,5,6;7,8,9");
		sut = new RepeatControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		//when
		sut.run();
		//then
		new Verifications() {{
			command.run();
			times = 3;
		}
		};
	}
	
	
	
	@Test
	public void shouldRepeatWithInlineDataWithPropertyValueCheck(
			@Mocked final Command command, @Mocked final WebSuitesUserProperties mockedProps) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3;4,5,6;7,8,9");
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
		new VerificationsInOrder() {{
			mockedProps.setProperty("1", "1");
			mockedProps.setProperty("2", "2");
			mockedProps.setProperty("3", "3");
			mockedProps.setProperty("1", "4");
			mockedProps.setProperty("2", "5");
			mockedProps.setProperty("3", "6");
			mockedProps.setProperty("1", "7");
			mockedProps.setProperty("2", "8");
			mockedProps.setProperty("3", "9");
		}
		};
	}
	
	
	
	@Test
	public void shouldRepeatWithInlineDataWithInlinePropertyNames(
			@Mocked final Command command, @Mocked final WebSuitesUserProperties mockedProps) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3;4,5,6;7,8,9");
		params.put("params", "param1,j,text");
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
		new VerificationsInOrder() {{
			mockedProps.setProperty("param1", "1");
			mockedProps.setProperty("j", "2");
			mockedProps.setProperty("text", "3");
			mockedProps.setProperty("param1", "4");
			mockedProps.setProperty("j", "5");
			mockedProps.setProperty("text", "6");
			mockedProps.setProperty("param1", "7");
			mockedProps.setProperty("j", "8");
			mockedProps.setProperty("text", "9");
		}
		};
	}
	
	
	
	@Parameters
	@Test
	public void shouldThrowExceptionWhenParsingInlineDataWithParams(String data) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", data);
		params.put("params", "x,y");
		sut = new RepeatControlFlowHandler(params);
		// and
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Error while parsing data string");
		sut.run();
		//when
		//then
		
	}
	
	@SuppressWarnings("unused")
	private Object[] parametersForShouldThrowExceptionWhenParsingInlineDataWithParams() {
		return $(
			$(""),
			$("1"),
			$("1,2,3"),
			$("1,2;1"),
			$("1,2;1,2,3"),
			$("1,2;1,2;1,2;1,2;1"),
			$("1,2;1,2;1,2;1,2;1,2,3,4,5,6")
		);
	}
	



}
