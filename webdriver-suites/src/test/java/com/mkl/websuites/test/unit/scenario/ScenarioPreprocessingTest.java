package com.mkl.websuites.test.unit.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.internal.scenario.ScenarioFilePreprocessor;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.test.core.ServiceBasedTest;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScenarioPreprocessingTest extends ServiceBasedTest {

	

	private static final String BASE_PATH = "src/test/resources/unit/scenarios/";


	@Override
	protected Class<?> getServiceUnderTest() {
		return ScenarioFilePreprocessor.class;
	}


	@Test
	public void testEmptyFile() {
		
		
		List<String> lines = ServiceFactory.get(ScenarioFilePreprocessor.class)
				.preprocessScenarioFile(
						new File(BASE_PATH
								+ "emptyScnFile.scn"));
		
		assertNotNull(lines);
		assertEquals(0, lines.size());
	}
	
	
	@Test
	public void testSingleFile() {
		
		
		List<String> lines = ServiceFactory.get(ScenarioFilePreprocessor.class)
				.preprocessScenarioFile(
						new File(BASE_PATH + "justLines.scn"));
		
		assertNotNull(lines);
		assertEquals(5, lines.size());
	}
	
	
	
	@Test
	public void testPreprocessingEmptyLines() {
		
		
		List<String> lines = ServiceFactory.get(ScenarioFilePreprocessor.class)
				.preprocessScenarioFile(
						new File(BASE_PATH + "someEmptyLines.scn"));
		
		assertNotNull(lines);
		assertEquals(6, lines.size());
	}
	
	
	@Test
	public void testPreprocessingWithComments() {
		
		
		List<String> lines = ServiceFactory.get(ScenarioFilePreprocessor.class)
				.preprocessScenarioFile(
						new File(BASE_PATH + "someEmptyLinesAndComments.scn"));
		
		assertNotNull(lines);
		assertEquals(4, lines.size());
	}
	
	
	
	@Test
	public void testWhiteSpaceTrimming() {
		
		
		List<String> lines = ServiceFactory.get(ScenarioFilePreprocessor.class)
				.preprocessScenarioFile(
						new File(BASE_PATH + "whitespaces.scn"));
		
		assertNotNull(lines);
		assertEquals(4, lines.size());
		
		assertEquals("content", lines.get(0));
	}
}