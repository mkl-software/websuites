/**
 * Copyright 2015 MKL Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mkl.websuites.internal.command;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import mockit.Deencapsulation;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.internal.command.CommandParser;
import com.mkl.websuites.internal.scenario.SourceLine;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.itests.cmd.MultiArgCommand;
import com.mkl.websuites.itests.cmd.NoArgCommand;
import com.mkl.websuites.itests.cmd.SampleCommand;
import com.mkl.websuites.itests.web.core.ServiceBasedTest;



@SuppressWarnings("rawtypes")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StandardCommandParserTest extends ServiceBasedTest {



    @Override
    protected Class<?> getServiceUnderTestClass() {
        return CommandParser.class;
    }



    @Test
    public void testTokenizingOneToken() {

        String line = "trimmed Value with spaces";

        String[] tokens = tokenize(line);

        assertEquals(1, tokens.length);

        assertEquals(line, tokens[0]);
    }


    @Test
    public void testTokenizingTwoTokens() {

        String line = "trimmed Value with spaces	second value";

        String[] tokens = tokenize(line);

        assertEquals(2, tokens.length);

        assertEquals("second value", tokens[1]);
    }



    @Test
    public void testTokenizingMultiTabs() {

        String line = "trimmed Value with spaces				" + "second value		third with spaces			last";

        String[] tokens = tokenize(line);

        assertEquals(4, tokens.length);

        assertEquals("last", tokens[3]);
    }



    private String[] tokenize(String line) {

        CommandParser logic = ServiceFactory.get(CommandParser.class);
        String[] tokens = Deencapsulation.invoke(logic, "tokenizeLine", line);
        return tokens;
    }



    @Test
    public void testParseCommandFromFile1() {
        CommandParser logic = ServiceFactory.get(CommandParser.class);
        List<SourceLine> lines = sourceLineFromStrings("sample\tsample command one string arg", "noArg\t");
        List<Command> parsedCommands = logic.parseCommandFromFile(lines);
        assertNotNull(parsedCommands);
        assertEquals(2, parsedCommands.size());
        assertThat(parsedCommands.get(0), instanceOf(SampleCommand.class));
        assertThat(parsedCommands.get(1), instanceOf(NoArgCommand.class));
    }


    private List<SourceLine> sourceLineFromStrings(String... strings) {
        List<SourceLine> result = new ArrayList<>();
        for (String string : strings) {
            result.add(new SourceLine(string, "", 0));
        }
        return result;
    }



    @Test
    public void testParseCommandFromFile2() {
        CommandParser logic = ServiceFactory.get(CommandParser.class);
        List<Command> parsedCommands = logic.parseCommandFromFile(sourceLineFromStrings());
        assertNotNull(parsedCommands);
        assertEquals(0, parsedCommands.size());
    }

    @Test
    public void testParseCommandFromFile3() {
        CommandParser logic = ServiceFactory.get(CommandParser.class);
        List<SourceLine> lines =
                sourceLineFromStrings("multiArg\tsome strong\t5\ttrue\t3", "noArg\t",
                        "sample\tsample command one string arg");
        List<Command> parsedCommands = logic.parseCommandFromFile(lines);
        assertNotNull(parsedCommands);
        assertEquals(3, parsedCommands.size());
        assertThat(parsedCommands.get(0), instanceOf(MultiArgCommand.class));
        assertThat(parsedCommands.get(1), instanceOf(NoArgCommand.class));
        assertThat(parsedCommands.get(2), instanceOf(SampleCommand.class));
        assertEquals(3, ((MultiArgCommand) parsedCommands.get(0)).getByte());
    }
}
