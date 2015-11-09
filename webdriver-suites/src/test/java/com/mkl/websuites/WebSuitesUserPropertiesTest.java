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
package com.mkl.websuites;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Deencapsulation;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.CommonUtils;
import com.mkl.websuites.internal.command.impl.misc.SetPropCommand;
import com.mkl.websuites.internal.services.ServiceFactory;



@RunWith(JUnitParamsRunner.class)
public class WebSuitesUserPropertiesTest {


    @WebSuites
    public static class MockRunner extends WebSuitesRunner {
    }

    @BeforeClass
    public static void init() {
        Deencapsulation.setField(ServiceFactory.class, "isInitialized", false);
        WebSuitesConfig.initializeWebsuitesConfig(MockRunner.class);
        ServiceFactory.init();
    }


    @Test
    public void testPopulatePropertiesCommand() {

        Map<String, String> props = new HashMap<String, String>();

        props.put("one", "valueOne");
        props.put("two", "valueThree");
        props.put("three", "valueThree");

        Command populatePropsCommand = new SetPropCommand(props);

        populatePropsCommand.run();

        for (String key : props.keySet()) {
            assertEquals(props.get(key), WebSuitesUserProperties.get().getProperty(key));
        }
    }



    @Test
    public void testLoadAndConversions() throws FileNotFoundException {
        WebSuitesUserProperties props = WebSuitesUserProperties.get();
        props.clear();
        props.load(new FileInputStream("src/test/resources/unit/props/props1.properties"));
        assertEquals("marcin", props.getProperty("name"));
        assertEquals("klosinski", props.getProperty("lastName"));
        assertEquals(30, props.getNumberProperty("age"));
        assertEquals(true, props.getBooleanProperty("active"));
        assertEquals(false, props.getBooleanProperty("banned"));
    }



    @Test
    public void testLoadSetUnset() throws FileNotFoundException {
        WebSuitesUserProperties props = WebSuitesUserProperties.get();
        props.clear();
        props.load(new FileInputStream("src/test/resources/unit/props/props1.properties"));
        props.unset("lastName");
        props.unset("banned");
        assertTrue(props.isSet("name"));
        assertFalse(props.isSet("lastName"));
        assertTrue(props.isSet("age"));
        assertTrue(props.isSet("active"));
        assertFalse(props.isSet("banned"));
    }



    @Parameters
    @Test
    public void testPropertyResolutionInStrings(String line, Map<String, String> props, String expectedLine) {

        WebSuitesUserProperties globalProps = WebSuitesUserProperties.get();
        globalProps.clear();
        globalProps.populateFrom(props);
        String result = CommonUtils.populateStringWithProperties(line);
        assertEquals(expectedLine, result);
    }



    @Test
    public void shouldContainSystemProperties() {
        // given
        Deencapsulation.setField(WebSuitesUserProperties.class, "instance", null);
        WebSuitesUserProperties props = WebSuitesUserProperties.get();
        // then
        assertThat(props.isSet("env.user.home")).isTrue();
        assertThat(props.isSet("env.java.home")).isTrue();
        assertThat(props.isSet("env.file.encoding")).isTrue();
    }



    @SuppressWarnings({"unused", "serial"})
    private Object[] parametersForTestPropertyResolutionInStrings() {
        return $(
                $("", new HashMap<String, String>(), ""),
                $("line without properties", new HashMap<String, String>(), "line without properties"),
                $("my name is ${name}", new HashMap<String, String>() {
                    {
                        put("name", "Marcin");
                    }
                }, "my name is Marcin"),
                $("my age is ${name} and I'm ${status} doing ${count} activities at ${place}",
                        new HashMap<String, String>() {
                            {
                                put("name", "Adam");
                                put("status", "active");
                                put("count", "24");
                                put("place", "gym");
                            }
                        }, "my age is Adam and I'm active doing 24 activities at gym"),
                $("missing property value ${prop}", new HashMap<String, String>() {
                    {
                        put("another", "value");
                    }
                }, "missing property value ${prop}"));
    }
}
