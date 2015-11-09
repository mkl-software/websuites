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
package com.mkl.websuites.internal.scenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.WebSuitesException;


@Slf4j
public class StandardScenarioFilePreprocessor implements ScenarioFilePreprocessor {



    private static StandardScenarioFilePreprocessor instance = new StandardScenarioFilePreprocessor();


    public static StandardScenarioFilePreprocessor getInstance() {
        return instance;
    }



    @Override
    public List<SourceLine> preprocessScenarioFile(File scenarioFile) {


        try (BufferedReader br = new BufferedReader(new FileReader(scenarioFile))) {

            String line;

            List<SourceLine> lines = new ArrayList<SourceLine>();

            int lineNumber = 0;

            while ((line = br.readLine()) != null) {

                lineNumber++;
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {

                    lines.add(new SourceLine(line, scenarioFile.getAbsolutePath(), lineNumber));
                }
            }

            return lines;

        } catch (IOException e) {

            String msg =
                    "Error while reading scenario file: " + scenarioFile.getAbsolutePath() + ", message: "
                            + e.getLocalizedMessage();
            log.error(msg);

            throw new WebSuitesException(msg);
        }
    }


}
