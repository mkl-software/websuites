package com.mkl.websuites.internal.scenario;

import lombok.Data;

@Data
public class SourceLine {

	
	private String line;
	
	private String file;
	
	private int lineNumber;
	

	private SourceLine parent;

	public String printSourceInfo() {
		return String.format("Scenario file: %s\nLine number: %s\nLine: %s", file, lineNumber, line);
	}

	public SourceLine(String line, String file, int lineNumber) {
		super();
		this.line = line;
		this.file = file;
		this.lineNumber = lineNumber;
	}
}
