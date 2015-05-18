package com.mkl.websuites.tests;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.MultiBrowserSuite;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public class ScenarioFolderTest extends MultiBrowserSuite {

	
	private boolean ignoreSubfolders;




	@Override
	protected List<Test> defineTests() {
		
		Folders folderConfig = this.getClass().getAnnotation(Folders.class);
		
		if (folderConfig == null) {
			throw new WebSuitesException("Missing com.mkl.websuites.tests.Folders annotation");
		}
		
		ignoreSubfolders = folderConfig.ignoreSubfolders();
		
		List<Test> topLevelFolderSuites = new ArrayList<Test>();
		
		for (String path : folderConfig.path()) {
			
			TestSuite topLeveLFolderSuite = new TestSuite(path);
			
			processRecursivelyFolder(path, topLeveLFolderSuite);
			
			topLevelFolderSuites.add(topLeveLFolderSuite);
		}
		
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
		
		for (File nested : nestedFolders) {
			
			processRecursivelyFolder(nested.getAbsolutePath(), currentFolderSuite);
		}
		
	}




	protected List<Test> processScenarioFilesInFolder(String folderPath) {

		
		File folder = new File(folderPath);
		
		ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);
		
		File[] scenarioFiles = folder.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".scn");
			}
		});
		
		List<Test> testsInThisFolder = new ArrayList<Test>();
		
		for (File scnearioFile : scenarioFiles) {
			
			List<Test> testsInScenarioFile =
					scenarioFileProcessor.processSingleScenarioFile(scnearioFile.getAbsolutePath());
			
			testsInThisFolder.addAll(testsInScenarioFile);
		}
		
		return testsInThisFolder;
	}

}
