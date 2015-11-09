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
package com.mkl.websuites.internal.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public class ScenarioFileTest extends MultiBrowserSuite {



    // TODO : TEMP for compilation fixing
    public ScenarioFileTest() {}

    public ScenarioFileTest(String path) {
        super(path);
    }


    @Override
    protected List<Test> defineTests() {

        ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);

        List<Test> scenarioTests = new ArrayList<Test>();

        String path = (String) genericParams[0];

        scenarioTests.addAll(scenarioFileProcessor.processSingleScenarioFile(path));

        return scenarioTests;

    }


}
