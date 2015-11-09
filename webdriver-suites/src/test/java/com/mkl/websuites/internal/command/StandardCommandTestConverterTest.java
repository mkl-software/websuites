package com.mkl.websuites.internal.command;

import static java.util.Arrays.asList;
import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestSuite;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Deencapsulation;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.internal.command.impl.flow.RepeatControlFlowHandler;
import com.mkl.websuites.itests.cmd.SampleCommand;


@RunWith(JUnitParamsRunner.class)
public class StandardCommandTestConverterTest {



    private StandardCommandTestConverter sut = new StandardCommandTestConverter();

    private Command simpleCommand;
    private RepeatControlFlowHandler subtestRepeat;

    private enum ExpectedTestType {
        TEST_CASE, TEST_SUITE
    }

    {
        simpleCommand = new SampleCommand("");
        Map<String, String> params = new HashMap<String, String>();
        params.put("subtest", "true");
        subtestRepeat = new RepeatControlFlowHandler(params);
    }



    @Test
    public void shouldDoPlainConversionForNoConstrolFlowCommands() {
        // given
        Command command = new SampleCommand("");
        List<Command> commands = Arrays.asList(command, command, command);
        // when
        List<junit.framework.Test> convertedTests = sut.convertCommandsToTests(commands, "");
        // then
        assertThat(convertedTests).hasSize(1);
    }



    @Test
    public void shoulDoConversionForOneSubtestControlFlowCommandOnlyRepeat() {

        List<junit.framework.Test> innerTestsInsideSuiteScenario =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList((Command) subtestRepeat));

        // should be: empty suite for repeat subtests
        assertTestTypesInsideMasterSuite(innerTestsInsideSuiteScenario, ExpectedTestType.TEST_SUITE);

        // and inside that suite there should no be any test cases:
        assertThat(Collections.list(((TestSuite) innerTestsInsideSuiteScenario.get(0)).tests())).hasSize(0);
    }



    @Test
    public void shoulDoConversionForOneSubtestRepeatWithProceedingAndFollowing() {

        // given
        List<junit.framework.Test> innerTestsInsideSuiteScenario =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList(simpleCommand, subtestRepeat, simpleCommand,
                        simpleCommand));

        // should be: test for before-repeat commands -> empty suite for repeat subtests ->
        // test for after repeat commands:

        assertTestTypesInsideMasterSuite(innerTestsInsideSuiteScenario, ExpectedTestType.TEST_CASE,
                ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_CASE);

    }



    @Test
    public void shoulDoConversionForSubtestRepeatWithoutPreceeding() {

        // given
        List<junit.framework.Test> innerTestsInsideSuiteScenario =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList(subtestRepeat, simpleCommand, simpleCommand));

        // should be: empty suite for repeat subtests -> test for after repeat commands:

        assertTestTypesInsideMasterSuite(innerTestsInsideSuiteScenario, ExpectedTestType.TEST_SUITE,
                ExpectedTestType.TEST_CASE);

    }



    @Test
    public void shoulDoConversionForSubtestRepeatWithoutFollowing() {

        // given
        List<junit.framework.Test> innerTestsInsideSuiteScenario =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList(simpleCommand, simpleCommand, subtestRepeat));

        // should be: test for before repeat commands -> empty suite for repeat subtests

        assertTestTypesInsideMasterSuite(innerTestsInsideSuiteScenario, ExpectedTestType.TEST_CASE,
                ExpectedTestType.TEST_SUITE);

    }



    @Test
    public void shoulDoConversionForSubtestRepeatSevenRepeatsOnly() {

        // given
        List<junit.framework.Test> innerTestsInsideSuiteScenario =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList((Command) subtestRepeat, subtestRepeat,
                        subtestRepeat, subtestRepeat, subtestRepeat, subtestRepeat, subtestRepeat));

        // should be: seven suites only

        assertTestTypesInsideMasterSuite(innerTestsInsideSuiteScenario, ExpectedTestType.TEST_SUITE,
                ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_SUITE,
                ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_SUITE);

    }



    @Test
    public void shoulDoConversionForSubtestRepeatComplexCase() {

        // given
        List<junit.framework.Test> innerTestsInsideSuiteScenario =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList(subtestRepeat, simpleCommand, simpleCommand,
                        subtestRepeat, simpleCommand, simpleCommand, simpleCommand, subtestRepeat, subtestRepeat,
                        simpleCommand, subtestRepeat, simpleCommand, simpleCommand));

        // should be: test suite for first repeat -> test case for 2 commands -> test suite for
        // repeat,
        // test case for 3 commands -> test suite for repeat -> test suite for repeat -> test case
        // for 1 command ->
        // test suite for repeat -> test case for 2 last commands

        assertTestTypesInsideMasterSuite(innerTestsInsideSuiteScenario, ExpectedTestType.TEST_SUITE,
                ExpectedTestType.TEST_CASE, ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_CASE,
                ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_CASE,
                ExpectedTestType.TEST_SUITE, ExpectedTestType.TEST_CASE);

    }



    private List<junit.framework.Test> checkSubTestMasterSuitAndGetInnerTests(List<Command> sourceCommands) {

        // when
        List<junit.framework.Test> convertedTests = sut.convertCommandsToTests(sourceCommands, "");
        // then
        assertThat(convertedTests).hasSize(1); // one master suite for all inner test definitions
        assertThat(convertedTests.get(0)).isInstanceOf(TestSuite.class);
        TestSuite masterSuite = (TestSuite) convertedTests.get(0);
        List<junit.framework.Test> innerTestsInsideSuiteScenario = Collections.list(masterSuite.tests());
        return innerTestsInsideSuiteScenario;
    }



    private void assertTestTypesInsideMasterSuite(List<junit.framework.Test> innerTestsInsideSuiteScenario,
            ExpectedTestType... expectedTypes) {

        assertThat(innerTestsInsideSuiteScenario).hasSize(expectedTypes.length);

        int idx = 0;
        for (ExpectedTestType testType : expectedTypes) {

            if (testType == ExpectedTestType.TEST_CASE) {

                assertThat(innerTestsInsideSuiteScenario.get(idx)).isInstanceOf(junit.framework.Test.class)
                        .isNotInstanceOf(TestSuite.class);

            } else if (testType == ExpectedTestType.TEST_SUITE) {

                assertThat(innerTestsInsideSuiteScenario.get(idx)).isExactlyInstanceOf(TestSuite.class);
            }

            idx++;
        }
    }



    @Parameters(value = {",0", "1,1", "1;2,2", "1;2;3;4;5;6;7;8;9, 9"})
    @Test
    public void shouldDoConversionForRepeatWithDddlParams(String inlineData, int ddlCasesCount) {
        // given
        Map<String, String> params = new HashMap<String, String>();
        params.put("subtest", "true");
        params.put("data", inlineData);
        RepeatControlFlowHandler subtestRepeatWithDDl = new RepeatControlFlowHandler(params);
        // when
        List<junit.framework.Test> innerTests =
                checkSubTestMasterSuitAndGetInnerTests(Arrays.asList((Command) subtestRepeatWithDDl));
        // then
        assertTestTypesInsideMasterSuite(innerTests, ExpectedTestType.TEST_SUITE);

        assertThat(Collections.list(((TestSuite) innerTests.get(0)).tests())).hasSize(ddlCasesCount);

    }



    @Parameters(method = "provideCommandListsWithNoSubtests")
    @Test
    public void shouldNotContainSubtest(List<Command> inputList) {
        // given inputList
        // when
        sut.checkRecursivelyForSubtests(inputList);
        boolean containsSubtests = Deencapsulation.getField(sut, "hasSubtests");
        // then
        assertThat(containsSubtests).isFalse();
    }


    @Parameters(method = "provideCommandListsWithSubtests")
    @Test
    public void shouldContainSubtest(List<Command> inputList) {
        // given inputList
        // when
        sut.checkRecursivelyForSubtests(inputList);
        boolean containsSubtests = Deencapsulation.getField(sut, "hasSubtests");
        // then
        assertThat(containsSubtests).isTrue();
    }



    @SuppressWarnings({"unused", "serial"})
    private Object[] provideCommandListsWithNoSubtests() {
        return $($(asList()), $(asList((Command) new SampleCommand(""), new SampleCommand(""), new SampleCommand(""))),
                $(asList((Command) new SampleCommand(""), new SampleCommand(""), new RepeatControlFlowHandler())),
                $(asList(new RepeatControlFlowHandler(new HashMap<String, String>() {
                    {
                        put("subtest", "false");
                    }
                }))));
    }



    @SuppressWarnings({"unused", "serial"})
    private Object[] provideCommandListsWithSubtests() {

        RepeatControlFlowHandler parentCommand = new RepeatControlFlowHandler(new HashMap<String, String>() {
            {
                put("subtest", "false");
            }
        });

        RepeatControlFlowHandler nested = new RepeatControlFlowHandler(new HashMap<String, String>() {
            {
                put("subtest", "true");
            }
        });

        parentCommand.setNestedCommands(asList((Command) nested));

        return $($(asList(parentCommand)), $(asList(new RepeatControlFlowHandler(new HashMap<String, String>() {
            {
                put("subtest", "true");
            }
        }))));
    }
}
