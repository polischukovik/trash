package com.yet.spring.core.loggers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class FileEventLogger implements EventLogger {
	private String fileName;
	private File file;
	
	public FileEventLogger(String fileName){
		this.fileName = fileName;		
	}
	
	private void init() throws IOException{
		this.file = new File(fileName);
		this.file.createNewFile();
		if (!this.file.canWrite()) throw new IOException("File is not accessible: " + file.getAbsolutePath() );
	}

	public void logEvent(Event event) {
		try {
			FileUtils.writeStringToFile(file, event.getMessage() + "\n", Charset.defaultCharset(), true);
		} catch (IOException e) {
			System.err.println("Cannot write to a file " + fileName);
		}
	}

}
