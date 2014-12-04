package com.mkl.websuites.internal.command.impl.flow.repeat;

import static junitparams.JUnitParamsRunner.$;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Verifications;
import mockit.VerificationsInOrder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;



@RunWith(JUnitParamsRunner.class)
public class InlineDataRepeatHandlerTest {

	
	private InlineDataRepeatHandler sut;
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
	
	
	@Test
	public void shouldRepeatWithInlineDataSimpleVersion(@Mocked final Command command) {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "1,2,3;4,5,6;7,8,9");
		sut = new InlineDataRepeatHandler(params);
		//when
		sut.doRepeat(Arrays.asList(command));
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
		sut = new InlineDataRepeatHandler(params);
		// and
		new NonStrictExpectations() {{
			WebSuitesUserProperties.get();
			result = mockedProps;
		}
		};
		//when
		sut.doRepeat(Arrays.asList(command));
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
		sut = new InlineDataRepeatHandler(params);
		// and
		new NonStrictExpectations() {{
			WebSuitesUserProperties.get();
			result = mockedProps;
		}
		};
		//when
		sut.doRepeat(Arrays.asList(command));
		//then
		new VerificationsInOrder() {{
			mockedProps.setProperty("param1", "1");
			mockedProps.setProperty("j", "2");
			mockedProps.setProperty("text", "3");
			command.run();
			mockedProps.setProperty("param1", "4");
			mockedProps.setProperty("j", "5");
			mockedProps.setProperty("text", "6");
			command.run();
			mockedProps.setProperty("param1", "7");
			mockedProps.setProperty("j", "8");
			mockedProps.setProperty("text", "9");
			command.run();
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
		sut = new InlineDataRepeatHandler(params);
		// and
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Error while parsing data string");
		sut.doRepeat(null);
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
