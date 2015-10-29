package com.mkl.websuites.internal.command.impl.flow.repeat;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.internal.WebSuitesException;



@RunWith(JUnitParamsRunner.class)
public class InlineDataProviderTest {

	
	private InlineDataProvider sut;
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
	
	@Test
	public void shouldParseEmptyDataString() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", "");
		sut = new InlineDataProvider(params);
		//when
		List<Map<String,String>> data = sut.provideData();
		//then
		assertThat(data).hasSize(0);
		
	}
	
	
	@Test
	public void shouldAllowEmptyStringInParamValues() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", ",a,b,c");
		sut = new InlineDataProvider(params);
		//when
		List<Map<String,String>> data = sut.provideData();
		//then
		assertThat(data).hasSize(1);
		assertThat(data.get(0)).hasSize(4);
		assertThat(data.get(0).get("1")).isEmpty();
		
	}
	
	
	@Parameters
	@Test
	public void shouldThrowExceptionWhenParsingInlineDataWithParams(String data) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("data", data);
		params.put("params", "x,y"); // expecting 2 params in inline data, otherwise expected exception
		sut = new InlineDataProvider(params);
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Error while parsing data string");
		
		// then
		sut.provideData();
		
	}
	
	@SuppressWarnings("unused")
	private Object[] parametersForShouldThrowExceptionWhenParsingInlineDataWithParams() {
		return $(
			$("1"),
			$("1,2,3"),
			$("1,2;1"),
			$("1,2;1,2,3"),
			$("1,2;1,2;1,2;1,2;1"),
			$("1,2;1,2;1,2;1,2;1,2,3,4,5,6")
		);
	}
	
	
}
