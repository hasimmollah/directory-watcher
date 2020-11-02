package com.hicx.directorywatcher.processor;

import java.nio.file.Path;

import com.hicx.directorywatcher.DataExtractor;

public interface FileProcessor {

	void process(Path fileDetails, DataExtractor dataExtractor);

	
}
