package com.BackEndTest.LogResource;

import java.io.File;

public class Log {

	private File logFile;
	private String name;
	
	public Log() {
		
	}
	
	public Log(File logFile, String name) {
		this.logFile = logFile;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getLogFile() {
		return logFile;
	}

	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}
	
}
