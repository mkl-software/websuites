package com.mkl.websuites.test.core;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyBasedTest {

	
	private static Server server;



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
			server.start();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	
	@AfterClass
	public static void shutdownServer() {
		
		try {
			
			server.stop();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
