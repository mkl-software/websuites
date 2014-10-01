package com.mkl.websuites;

public class WebSuitesException extends RuntimeException {

	public WebSuitesException(String string) {
		super(string);
	}
	
	
	public WebSuitesException(String string, Throwable rootCause) {
		super(string, rootCause);
	}

	private static final long serialVersionUID = 2040992221530691068L;

	
	
}