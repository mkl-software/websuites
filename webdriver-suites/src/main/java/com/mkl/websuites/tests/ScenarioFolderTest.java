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

	@Override
	protected List<Test> defineTests() {
		
		Folders folderConfig = this.getClass().getAnnotation(Folders.class);
		
		if (folderConfig == null) {
			throw new WebSuitesException("Missing com.mkl.websuites.tests.Folders annotation");
		}
		
		boolean ignoreSubfolders = folderConfig.ignoreSubfolders();
		
		List<Test> topLevelFolderSuites = new ArrayList<Test>();
		
		for (String path : folderConfig.path()) {
			
			TestSuite topLeveLTestSuite = processScenarioFilesInFolder(path);
			
			if (!ignoreSubfolders) {
				processRecursivelyFolder(path, topLeveLTestSuite);
			}
			
			topLevelFolderSuites.add(topLeveLTestSuite);
		}
		
		return topLevelFolderSuites;
	}

	
	
	
	private void processRecursivelyFolder(String folderPath, TestSuite parentSuite) {

		File folder = new File(folderPath);
		
		File[] nestedFolders = folder.listFiles(new FileFilter() {
		
			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
		
		for (File nested : nestedFolders) {
			
			TestSuite nestedFolderSuite = processScenarioFilesInFolder(nested.getAbsolutePath());
			
			parentSuite.addTest(nestedFolderSuite);
		}
	}




	protected TestSuite processScenarioFilesInFolder(String folderPath) {

		TestSuite folderSuite = new TestSuite(folderPath);
		
		File folder = new File(folderPath);
		
		ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);
		
		File[] scenarioFiles = folder.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".scn");
			}
		});
		
		for (File scnearioFile : scenarioFiles) {
			
			List<Test> testsInScenarioFile =
					scenarioFileProcessor.processSingleScenarioFile(scnearioFile.getAbsolutePath());
			
			for (Test scenarioTest : testsInScenarioFile) {
				
				folderSuite.addTest(scenarioTest);
			}
		}
		
		return folderSuite;
	}

}
