package com.hicx.directorywatcher.processor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.hicx.directorywatcher.ApplicationConstants;
import com.hicx.directorywatcher.DataExtractor;
import com.hicx.directorywatcher.exception.ApplicationException;

public class TextFileProcessor implements FileProcessor{
	final static Logger LOGGER = Logger.getLogger(TextFileProcessor.class);


	public void process(Path fileDetails, DataExtractor dataExtractor) {
		LOGGER.info("Started process method");
		try {
			File file = new File(fileDetails.toAbsolutePath().toString());
		LOGGER.info("Started processing file");
		 Files.lines(fileDetails, StandardCharsets.UTF_8).parallel().forEach(line->{
				dataExtractor.extractData(line);
			});
		LOGGER.info("Finished processing file");
		String userDirectory = FileUtils.getUserDirectory().getAbsolutePath();
		
		
		Path destinationPath = Paths.get(userDirectory+ApplicationConstants.PROCESED_DIRECTORY_PATH+"/"+file.getName());
		File destinationFile = destinationPath.toFile();
		if(destinationFile.exists()) {
			destinationFile.delete();
			LOGGER.info("File already exist and finished deleting");
		}
		FileUtils.moveFileToDirectory(
				file, 
			      FileUtils.getFile(userDirectory+ApplicationConstants.PROCESED_DIRECTORY_PATH), true);
		
		} catch(Exception e) {
			throw new ApplicationException(e);
		}
		
		
		LOGGER.info("Finished process method");
	}

}
