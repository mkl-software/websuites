package com.mkl.websuites.tests;

import org.junit.Rule;
import org.junit.Test;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuitesException;

public class ScenarioFolderTestTest {

	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();

	private static class MissingAnnontationFolderedTest extends ScenarioFolderTest {}
	
	
	@Test(expected = WebSuitesException.class)
	public void shouldThrowExceptionOnMissingAnnotation() {
		//given
		MissingAnnontationFolderedTest sut = new MissingAnnontationFolderedTest();
//		expectedException.expect(WebSuitesException.class);
//			.hasMessageContaining("Missing com.mkl.websuites.tests.Folders annotation");
		//when
		sut.defineTests();
		// then expect ex
	}
}
