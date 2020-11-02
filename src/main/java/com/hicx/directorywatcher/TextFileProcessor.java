package com.hicx.directorywatcher;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.hicx.directorywatcher.exception.ApplicationException;

public class TextFileProcessor implements FileProcessor{
	final static Logger LOGGER = Logger.getLogger(TextFileProcessor.class);


	public void process(Path fileDetails, DataExtractor dataExtractor) {
		try {
		FileInputStream fn = new FileInputStream(fileDetails.toAbsolutePath().toFile()); 
		
		List<String> lines = Files.readLines(new File(fileDetails.toAbsolutePath().toString()), Charsets.UTF_8);
		
		lines.stream().forEach(line->{
			dataExtractor.extractData(line);
		});
		
		} catch(Exception e) {
			throw new ApplicationException(e);
		}
	}

}
