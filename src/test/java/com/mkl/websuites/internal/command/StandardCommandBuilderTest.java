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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mockit.Deencapsulation;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.command.Command;
import com.mkl.websuites.config.Extension;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.scenario.SourceLine;
import com.mkl.websuites.itests.cmd.MultiArgCommand;
import com.mkl.websuites.itests.cmd.NoArgCommand;
import com.mkl.websuites.itests.cmd.SampleCommand;
import com.mkl.websuites.itests.custom.CustomCommandInClasspath;



@SuppressWarnings("rawtypes")
public class StandardCommandBuilderTest {


    private StandardCommandBuilder sut = new StandardCommandBuilder();



    private List<Object> convertArguments(String[] args, List types) {
        List<Object> values = Deencapsulation.invoke(sut, "convertArgumentsToProperTypes", args, types);
        return values;
    }


    @WebSuites
    public static class DefaultConfig extends WebSuitesRunner {}
    
    
    
    @BeforeClass
    public static void initWebSuitesConfig() {
        WebSuitesConfig.initializeWebsuitesConfig(DefaultConfig.class);
    }


    @Test
    public void testSampleCommandArgTypes() {


        Deencapsulation.invoke(sut, "scanClasspathForCommands");
        Map commandArgTypes = Deencapsulation.getField(sut, "commandTypesMap");

        assertTrue(commandArgTypes.size() > 0);

        List types = (List) commandArgTypes.get("sample");

        assertNotNull(types);

        assertEquals(1, types.size());
        assertEquals(String.class, types.get(0));
    }



    @Test
    public void testCommandTypeResolution0() {

        String[] args = new String[] {};
        List types = Arrays.asList(String.class);

        List<Object> values = convertArguments(args, types);

        assertNotNull(values);
        assertEquals(values.size(), 0);
    }

    @Test
    public void testCommandTypeResolution1() {

        String[] args = new String[] {"argument value"};
        List types = Arrays.asList(String.class);

        List<Object> values = convertArguments(args, types);

        assertNotNull(values);
        assertEquals(values.size(), 1);
        assertEquals("argument value", values.get(0));
    }



    @Test
    public void testCommandTypeResolution2() {

        String[] args = new String[] {"3", "6", "12"};
        List types = Arrays.asList(Integer.class, Short.class, Long.class);

        List<Object> values = convertArguments(args, types);

        assertNotNull(values);
        assertEquals(values.size(), 3);
        assertEquals(3, values.get(0));
        assertEquals((short) 6, values.get(1));
        assertEquals(12L, values.get(2));
    }



    @Test
    public void testCommandTypeResolution3() {

        String[] args = new String[] {"true", "-2", "something", "another"};
        List types = Arrays.asList(Boolean.class, Integer.class, String.class, String.class);

        List<Object> values = convertArguments(args, types);

        assertNotNull(values);
        assertEquals(values.size(), 4);
        assertTrue((boolean) values.get(0));
        assertEquals(-2, values.get(1));
        assertEquals("something", values.get(2));
        assertEquals("another", values.get(3));
    }



    @Test
    public void testSimpleCommand() {

        Command command = sut.instantiateCommand("sample", new String[] {"command argument"}, source());

        assertNotNull(command);

        assertTrue(command instanceof SampleCommand);

        assertEquals("command argument", Deencapsulation.getField(command, "arg"));
    }



    @Test
    public void testNoArgCommand() {

        Command command = sut.instantiateCommand("noArg", new String[] {}, source());

        assertNotNull(command);

        assertTrue(command instanceof NoArgCommand);
    }



    private SourceLine source() {
        return new SourceLine("", "", 0);
    }



    @Test
    public void testMultiArgCommand() {

        Command command =
                sut.instantiateCommand("multiArg", new String[] {"string value", "5687", "true", "23"}, source());

        assertNotNull(command);

        assertTrue(command instanceof MultiArgCommand);

        assertEquals("string value", Deencapsulation.getField(command, "string"));
        assertEquals(5687, Deencapsulation.getField(command, "integer"));
        assertEquals(true, Deencapsulation.getField(command, "bool"));
        assertEquals((byte) 23, Deencapsulation.getField(command, "bytee"));
    }



    @Test(expected = WebSuitesException.class)
    public void testArgumentErrorWrongArgumentCount() {
        sut.instantiateCommand("sample simple value", // without tabs
                new String[] {}, source());
        fail("command should not be craeted");
    }



    @Test(expected = WebSuitesException.class)
    public void testArgumentErrorToManyArguments() {
        sut.instantiateCommand("sample", new String[] {"param", "another not expected"}, source());
        fail("command should not be craeted");
    }



    @Test
    public void testParameterizedCommandMapParsing1() {
        String[] valueLine = new String[] {"param=value", "param2=value2", "param3=value3"};
        List result = convertParams(sut, valueLine);
        assertNotNull(result);
        assertEquals(1, result.size());
        Map paramMap = (Map) result.get(0);
        assertEquals(3, paramMap.size());
        assertEquals("value", paramMap.get("param"));
        assertEquals("value2", paramMap.get("param2"));
        assertEquals("value3", paramMap.get("param3"));
    }



    private List convertParams(CommandBuilder logic, String[] valueLine) {
        return Deencapsulation.invoke(logic, "convertArgumentsToParameterMap", (Object) valueLine);
    }


    @Test
    public void testParameterizedCommandMapParsing2() {
        String[] valueLine =
                new String[] {"param=value anotherThing space", "param2=value2=next=next=next=next=end", "param3"};
        List result = convertParams(sut, valueLine);
        assertNotNull(result);
        assertEquals(1, result.size());
        Map paramMap = (Map) result.get(0);
        assertEquals(3, paramMap.size());
        assertEquals("value anotherThing space", paramMap.get("param"));
        assertEquals("value2=next=next=next=next=end", paramMap.get("param2"));
        assertEquals("", paramMap.get("param3"));
    }
    
    
    
    @WebSuites(extension = @Extension(commandExtensionPackages = "com.mkl.websuites.itests.custom"))
    public static class CustomPackageRunner extends WebSuitesRunner {}
    
    @Test
    public void shouldDetectCustomScanPathPackage() {
        //given
        WebSuitesConfig.initializeWebsuitesConfig(CustomPackageRunner.class);
        sut = new StandardCommandBuilder();
        //when
        Command command = sut.instantiateCommand("customUserCommand", new String[] {}, new SourceLine("", "", 0));
        //then
        assertThat(command).isInstanceOf(CustomCommandInClasspath.class);
    }
}
