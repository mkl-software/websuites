package com.mkl.websuites.internal.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.internal.scenario.ScenarioFilePreprocessor;
import com.mkl.websuites.internal.scenario.SourceLine;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.itests.web.core.ServiceBasedTest;


@SuppressWarnings("rawtypes")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StandardScenarioFilePreprocessorTest extends ServiceBasedTest {



    private static final String BASE_PATH = "src/test/resources/unit/scenarios/";


    @Override
    protected Class<?> getServiceUnderTestClass() {
        return ScenarioFilePreprocessor.class;
    }


    @Test
    public void testEmptyFile() {


        List<SourceLine> lines =
                ServiceFactory.get(ScenarioFilePreprocessor.class).preprocessScenarioFile(
                        new File(BASE_PATH + "emptyScnFile.scn"));

        assertNotNull(lines);
        assertEquals(0, lines.size());
    }


    @Test
    public void testSingleFile() {


        List<SourceLine> lines =
                ServiceFactory.get(ScenarioFilePreprocessor.class).preprocessScenarioFile(
                        new File(BASE_PATH + "justLines.scn"));

        assertNotNull(lines);
        assertEquals(5, lines.size());
    }



    @Test
    public void testPreprocessingEmptyLines() {


        List<SourceLine> lines =
                ServiceFactory.get(ScenarioFilePreprocessor.class).preprocessScenarioFile(
                        new File(BASE_PATH + "someEmptyLines.scn"));

        assertNotNull(lines);
        assertEquals(6, lines.size());
    }


    @Test
    public void testPreprocessingWithComments() {


        List<SourceLine> lines =
                ServiceFactory.get(ScenarioFilePreprocessor.class).preprocessScenarioFile(
                        new File(BASE_PATH + "someEmptyLinesAndComments.scn"));

        assertNotNull(lines);
        assertEquals(4, lines.size());
    }



    @Test
    public void testWhiteSpaceTrimming() {


        List<SourceLine> lines =
                ServiceFactory.get(ScenarioFilePreprocessor.class).preprocessScenarioFile(
                        new File(BASE_PATH + "whitespaces.scn"));

        assertNotNull(lines);
        assertEquals(4, lines.size());

        assertEquals("content", lines.get(0).getLine());
    }
}
