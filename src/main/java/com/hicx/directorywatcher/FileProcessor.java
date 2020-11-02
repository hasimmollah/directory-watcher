package com.hicx.directorywatcher;

import java.nio.file.Path;

public interface FileProcessor {

	void process(Path fileDetails, DataExtractor dataExtractor);

	
}
