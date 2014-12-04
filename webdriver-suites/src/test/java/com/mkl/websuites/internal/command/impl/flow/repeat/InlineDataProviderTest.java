package com.mkl.websuites.internal.command.impl.flow.repeat;

import static junitparams.JUnitParamsRunner.$;

import java.util.HashMap;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuitesException;



@RunWith(JUnitParamsRunner.class)
public class InlineDataProviderTest {

	
	private InlineDataProvider sut;
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
	
	
	@Parameters
	@Test
	public void shouldThrowExceptionWhenParsingInlineDataWithParams(String data) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", data);
		params.put("params", "x,y");
		sut = new InlineDataProvider(params);
		// and
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Error while parsing data string");
		sut.provideData();
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
