package com.hicx.directorywatcher.processor;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.hicx.directorywatcher.ApplicationConstants;
import com.hicx.directorywatcher.DataExtractor;
import com.hicx.directorywatcher.exception.ApplicationException;

public class TextFileProcessor implements FileProcessor{
	final static Logger LOGGER = Logger.getLogger(TextFileProcessor.class);


	public void process(Path fileDetails, DataExtractor dataExtractor) {
		try {
			File file = new File(fileDetails.toAbsolutePath().toString());
		List<String> lines = Files.readLines(file, Charsets.UTF_8);
		lines.stream().forEach(line->{
			dataExtractor.extractData(line);
		});
		Files.move(file, new File(fileDetails.getFileName().toString()));
		
		} catch(Exception e) {
			throw new ApplicationException(e);
		}
	}

}
