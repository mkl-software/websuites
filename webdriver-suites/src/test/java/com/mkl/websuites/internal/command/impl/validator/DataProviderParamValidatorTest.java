package com.mkl.websuites.internal.command.impl.validator;

import org.junit.Rule;
import org.junit.Test;

import com.mkl.websuites.WebSuitesException;

import pl.wkr.fluentrule.api.FluentExpectedException;



public class DataProviderParamValidatorTest {

	
	
	DataProviderParamValidator sut = new DataProviderParamValidator();
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();
	
	@Test
	public void shouldPassValidation() {
		//given
		//when
		sut.validateParam("com.mkl.websuites.internal.command.impl.validator.SampleDataProvider");
		//then no validation exception thrown
	}
	
	
	@Test
	public void shouldNotPassValidationClassNotFound() {
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Cannot find data provider class");
		//then
		sut.validateParam("com.mkl.websuites.internal.command.impl.validator.NotExisting");
	}
	
	
	@Test
	public void shouldNotPassValidationClassNotImplementingDataProvider() {
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Data provider class")
			.hasMessageContaining("must implement");
		//then
		sut.validateParam("com.mkl.websuites.internal.command.impl.validator."
				+ "NotImplementingDataProvider");
	}
	
	
	
	@Test
	public void shouldNotPassValidationClassWithoutNoArgConstructor() {
		//when
		expectedException.expect(WebSuitesException.class)
			.hasMessageContaining("Make sure the class has a no-arg constructor");
		//then
		sut.validateParam("com.mkl.websuites.internal.command.impl.validator."
				+ "WithoutNoArgConstructor");
	}
}
