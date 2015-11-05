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
