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

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.xml.ws.WebServiceException;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public class ScenarioFolderTest extends MultiBrowserSuite {


    private boolean ignoreSubfolders;



    public ScenarioFolderTest(String path, boolean ignoreSubfolders, SortingStrategy sortingStrategy) {

        super(path, ignoreSubfolders, sortingStrategy);
    }



    @Override
    protected List<Test> defineTests() {

        String path = (String) genericParams[0];
        ignoreSubfolders = (boolean) genericParams[1];
        // SortingStrategy sortingStrategy = (SortingStrategy) genericParams[2];

        List<Test> topLevelFolderSuites = new ArrayList<Test>();

        TestSuite topLeveLFolderSuite = new TestSuite(path);

        processRecursivelyFolder(path, topLeveLFolderSuite);

        topLevelFolderSuites.add(topLeveLFolderSuite);

        return topLevelFolderSuites;
    }



    private void processRecursivelyFolder(String folderPath, TestSuite parentSuite) {

        TestSuite currentFolderSuite = new TestSuite(folderPath);

        List<Test> testsInCurrentFolder = processScenarioFilesInFolder(folderPath);

        for (Test test : testsInCurrentFolder) {
            currentFolderSuite.addTest(test);
        }

        parentSuite.addTest(currentFolderSuite);

        if (ignoreSubfolders) {
            return;
        }

        File folder = new File(folderPath);

        File[] nestedFolders = folder.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        if (nestedFolders == null) {
            throw new WebServiceException(String.format("Error while traversing through folder "
                    + "structure starting from path '%s'. Probably there is something wrong " + "in the path string.",
                    folderPath));
        }

        sort(nestedFolders);

        for (File nested : nestedFolders) {

            processRecursivelyFolder(nested.getAbsolutePath(), currentFolderSuite);
        }

    }



    private void sort(File[] files) {

        Arrays.sort(files, new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {

                return f1.getName().compareTo(f2.getName());
            }

        });
    }



    protected List<Test> processScenarioFilesInFolder(String folderPath) {


        File folder = new File(folderPath);

        ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);

        File[] scenarioFiles = folder.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.getName().toLowerCase(Locale.getDefault()).endsWith(".scn");
            }
        });

        if (scenarioFiles == null) {
            throw new WebServiceException(String.format("Error while reading scenario files in "
                    + "the folder path '%s'. Probably there is something wrong in the path string.", folderPath));
        }

        sort(scenarioFiles);

        List<Test> testsInThisFolder = new ArrayList<Test>();

        for (File scnearioFile : scenarioFiles) {

            List<Test> testsInScenarioFile =
                    scenarioFileProcessor.processSingleScenarioFile(scnearioFile.getAbsolutePath());

            testsInThisFolder.addAll(testsInScenarioFile);
        }

        return testsInThisFolder;
    }

}
