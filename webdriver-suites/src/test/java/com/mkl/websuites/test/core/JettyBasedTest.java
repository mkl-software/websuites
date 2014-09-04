package com.mkl.websuites.test.core;

import lombok.extern.slf4j.Slf4j;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;


@Slf4j
public class JettyBasedTest {

	
	private static Server server;


	protected int calculateExpectedRunCount(int underlyingTestCount, int browserCount) {
		
		// each test for every browser, + one browser startup test per browser + one close-up test
		return (underlyingTestCount + 1) * browserCount  + 1;
	}
	

	@BeforeClass
	public static void setupTestEnv() {
		
		server = new Server(90);
		
		server.setStopAtShutdown(true);
		
	    WebAppContext bb = new WebAppContext();
        bb.setServer(server);
        bb.setContextPath("/");
        bb.setWar("src/test/resources/webapp");
        server.addHandler(bb);
        
		try {
			
			log.debug("startig jetty server");
			server.start();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	@AfterClass
	public static void shutdownServer() {
		
		try {
			
			log.debug("stopping jetty server");
			server.stop();
			server.join();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
