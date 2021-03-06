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
package com.mkl.websuites.internal.command.impl.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.WebSuitesException;
import com.mkl.websuites.internal.command.impl.flow.repeat.InlineDataProvider;
import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatDataProvider;
import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatHandler;
import com.mkl.websuites.internal.command.impl.validator.DataProviderParamValidator;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.RepeatHandlerValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "repeat")
public class RepeatControlFlowHandler extends ControlFlowHandler implements Subtestable, DDlParameterized {



    private int ddlParameterIndex = -1;


    public RepeatControlFlowHandler() {
        super();
    }

    public RepeatControlFlowHandler(Map<String, String> parameterMap) {
        super(parameterMap);
    }



    @Override
    protected void runCommandWithParameters() {

        ddlParameterIndex = -1;

        if (parameterMap.containsKey("times")) {
            doRepeatNTimes();
        } else if (parameterMap.containsKey("data")) {
            doRepeatWithInlineData();
        } else if (parameterMap.containsKey("dataProvider")) {
            doRepeatWithDataProviderClass();
        } else if (parameterMap.containsKey("handler")) {
            doRepeatWithCustomRepeatHandlerClass();
        }
    }



    private void doRepeatNTimes() {
        WebSuitesUserProperties props = WebSuitesUserProperties.get();
        int howManyTimes = Integer.parseInt(parameterMap.get("times"));
        String counterProperty = "1";
        if (parameterMap.containsKey("counter")) {
            counterProperty = parameterMap.get("counter");
        }
        for (int i = 0; i < howManyTimes; i++) {
            props.setProperty(counterProperty, (i + 1) + "");
            runNestedCommands();
        }
    }



    private void doRepeatWithDataProviderClass() {

        String dataProviderClass = parameterMap.get("dataProvider");
        try {
            RepeatDataProvider dataProvider = (RepeatDataProvider) Class.forName(dataProviderClass).newInstance();

            runForDataProvider(dataProvider);

        } catch (Exception e) {
            throw new WebSuitesException("Unepected exception when trying to acquire data from " + "provider class "
                    + dataProviderClass, e);
        }
    }



    private void doRepeatWithInlineData() {

        runForDataProvider(new InlineDataProvider(parameterMap));
    }



    private void runForDataProvider(RepeatDataProvider dataProvider) {

        List<Map<String, String>> data = dataProvider.provideData();
        WebSuitesUserProperties userProperties = WebSuitesUserProperties.get();

        if (ddlParameterIndex == -1) {

            for (Map<String, String> params : data) {

                userProperties.populateFrom(params);
                runNestedCommands();
            }

        } else {

            userProperties.populateFrom(data.get(ddlParameterIndex));
            runNestedCommands();
        }

    }



    private void doRepeatWithCustomRepeatHandlerClass() {
        String handlerClass = parameterMap.get("handler");
        try {
            RepeatHandler handler = (RepeatHandler) Class.forName(handlerClass).newInstance();
            handler.doRepeat(nestedCommands);
        } catch (Exception e) {
            throw new WebSuitesException("Unepected exception when trying to initialize data "
                    + "repeat handler class " + handlerClass, e);
        }
    }



    private void runNestedCommands() {
        for (Command command : nestedCommands) {
            command.run();
        }
    }


    @Override
    protected List<SchemaValidationRule> defineValidationRules() {

        return Arrays.asList(new SchemaValidationRule("times").addOptionalElements("counter"),
                new SchemaValidationRule("data").addOptionalElements("params"),
                new SchemaValidationRule("data").addMandatoryElements("subtest").addOptionalElements("params")
                        .addOptionalElements("subtestName"), new SchemaValidationRule("dataProvider"),
                new SchemaValidationRule("handler"));
    }

    @Override
    protected List<ParameterValueValidator> defineParameterValueValidators() {

        return Arrays.asList((ParameterValueValidator) new IntegerNumberParamValidator("times"),
                new DataProviderParamValidator(), new RepeatHandlerValidator());
    }

    @Override
    public boolean isSubtest() {
        if (parameterMap.containsKey("subtest") && "TRUE".equalsIgnoreCase(parameterMap.get("subtest"))) {
            return true;
        }
        return false;
    }

    @Override
    public String getSubtestName() {
        String localName = parameterMap.get("subtestName");
        localName = localName == null ? "" : localName;
        return localName.isEmpty() ? "REPEAT" : localName;
    }


    /**
     * Invoked at test tree creation time, so data provider must be available before running real
     * test. Test case name consists of all parameter values separated by commas.
     */
    @Override
    public List<String> getSubTestCaseNames() {

        List<String> testCaseNames = new ArrayList<String>();

        if (parameterMap.containsKey("data")) {
            List<Map<String, String>> data = new InlineDataProvider(parameterMap).provideData();
            for (Map<String, String> dataRow : data) {
                StringBuilder testCaseName = new StringBuilder();
                for (String value : dataRow.values()) {
                    testCaseName.append(value).append(",");
                }
                testCaseName.deleteCharAt(testCaseName.length() - 1);

                testCaseNames.add(testCaseName.toString());
            }
        }

        return testCaseNames;
    }

    @Override
    public void runForDDlParam(int paramIndex) {

        if (parameterMap.containsKey("data")) {

            ddlParameterIndex = paramIndex;

            doRepeatWithInlineData();
        }
    }



    @Override
    public String toString() {
        return isSubtest() ? getSubtestName() : "Repeat, " + nestedCommands.size() + " nested commands";
    }



}
