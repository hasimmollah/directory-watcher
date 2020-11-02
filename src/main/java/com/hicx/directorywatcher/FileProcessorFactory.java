package com.hicx.directorywatcher;

import java.util.HashMap;
import java.util.Map;

import com.hicx.directorywatcher.exception.ApplicationException;

public class FileProcessorFactory {
	
	
	private static Map<String,FileProcessor> fileProcessorMap = new HashMap();
	static {
		fileProcessorMap.put("TEXT",new TextFileProcessor());
	}
	
	public static FileProcessor getFileProcessor(String extension) {
		FileProcessor fileProcessor = fileProcessorMap.get(extension);
		if(fileProcessor==null) {
			throw new ApplicationException("Extension Not supported");
		}
		return fileProcessor;
	}
	
	

}
