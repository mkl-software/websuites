package com.mkl.websuites.internal.command.impl.validator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.internal.WebSuitesException;


@RunWith(JUnitParamsRunner.class)
public class BooleanParamValidatorTest {

	
	private BooleanParamValidator sut = new BooleanParamValidator("testParam");
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	
	@Parameters({"true", "false", "TRUE", "FALSE", "True", "False"})
	@Test
	public void shouldPassBooleanParamValidation(String param) {
		//given param
		//when
		sut.validateParam(param);
		//then OK
	}
	
	
	
	@Parameters({"", "f", "t", "yes", "no", "TRUEe", "FALSEE"})
	@Test
	public void shouldNotPassBooleanParamValidation(String param) {
		//given
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("must be proper BOOLEAN value")
			.hasMessageContaining("testParam")
			.hasMessageContaining(param);
		//then
		sut.validateParam(param);
	}
	
	
	@Test
	public void shouldNotPassForNullValue() {
		//given
		expectedException.expect(WebSuitesException.class);
		//then
		sut.validateParam(null);
	}
}
