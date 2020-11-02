package com.hicx.directorywatcher.processor;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.hicx.directorywatcher.ApplicationConstants;
import com.hicx.directorywatcher.DataExtractor;
import com.hicx.directorywatcher.exception.ApplicationException;

public class TextFileProcessor implements FileProcessor{
	final static Logger LOGGER = Logger.getLogger(TextFileProcessor.class);


	public void process(Path fileDetails, DataExtractor dataExtractor) {
		try {
			File file = new File(fileDetails.toAbsolutePath().toString());
		List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
		lines.stream().forEach(line->{
			dataExtractor.extractData(line);
		});
		String userDirectory = FileSystems.getDefault()
                .getPath("")
                .toAbsolutePath()
                .toString();
		
		FileUtils.moveFileToDirectory(
				file, 
			      FileUtils.getFile(userDirectory+ApplicationConstants.PROCESED_DIRECTORY_PATH), true);
		
		} catch(Exception e) {
			throw new ApplicationException(e);
		}
	}

}
