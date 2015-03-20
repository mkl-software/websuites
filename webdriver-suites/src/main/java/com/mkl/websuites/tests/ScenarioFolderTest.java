package com.mkl.websuites.tests;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.mkl.websuites.internal.MultiBrowserSuite;
import com.mkl.websuites.internal.scenario.ScenarioFileProcessor;
import com.mkl.websuites.internal.services.ServiceFactory;

public class ScenarioFolderTest extends MultiBrowserSuite {

	@Override
	protected List<Test> defineTests() {
		
		Folders folderConfig = this.getClass().getAnnotation(Folders.class);
		
		boolean ignoreSubfolders = folderConfig.ignoreSubfolders();
		
		List<Test> topLevelFolderSuites = new ArrayList<Test>();
		
		for (String path : folderConfig.path()) {
			
			Test topLeveLTestSuite = buildTopLevelTestSuite(path, ignoreSubfolders);
			
			topLevelFolderSuites.add(topLeveLTestSuite);
		}
		
		return topLevelFolderSuites;
	}

	
	
	
	protected Test buildTopLevelTestSuite(String folderPath,
			boolean ignoreSubfolders) {

		TestSuite folderSuite = new TestSuite(folderPath);
		
		File folder = new File(folderPath);
		
		ScenarioFileProcessor scenarioFileProcessor = ServiceFactory.get(ScenarioFileProcessor.class);
		
		if (ignoreSubfolders) {
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
		}
		
		return folderSuite;
	}

}
