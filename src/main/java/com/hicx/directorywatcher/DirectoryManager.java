package com.hicx.directorywatcher;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.hicx.directorywatcher.processor.FileProcessor;
import com.hicx.directorywatcher.processor.FileProcessorFactory;

public class DirectoryManager {
	final static Logger LOGGER = Logger.getLogger(DirectoryManager.class);

	private WatchService registerPathAndPrepareWatchService(String directoryPath) {
		WatchService watchService = null;
		try {
			watchService = FileSystems.getDefault().newWatchService();

			Path path = Paths.get(directoryPath);

			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (Exception e) {

		}

		return watchService;

	}

	public void watchDirectory(String directoryPath) {
		processDirectoryAndPrepareStat(registerPathAndPrepareWatchService(directoryPath));
	}

	private void processDirectoryAndPrepareStat(WatchService watchService) {
		try {
			LOGGER.info("Started watching");
			WatchKey key;
			while ((key = watchService.take()) != null) {
						Path path = (Path) key.watchable();
				for (WatchEvent<?> event : key.pollEvents()) {

					try {
						LOGGER.info("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
						Path fileDetails = path.resolve(((WatchEvent<Path>) event).context());
						String fileFullPath = fileDetails.getFileName().toAbsolutePath().toString();
						LOGGER.info("Processing file : " + fileFullPath.toString());
						
						String extension = fileFullPath.substring(fileFullPath.lastIndexOf('.')+1);
						

						FileProcessor fileProcessor = FileProcessorFactory.getFileProcessor(extension.toUpperCase());
						ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap();
						DataExtractor dataExtractor = new DataExtractor(concurrentHashMap);
						fileProcessor.process(fileDetails, dataExtractor);

						long countWord = concurrentHashMap.get(ApplicationConstants.TOTAL_WORD)!=null?concurrentHashMap.get(ApplicationConstants.TOTAL_WORD):0;
						long countDot = concurrentHashMap.get(ApplicationConstants.DOT)!=null?concurrentHashMap.get(ApplicationConstants.DOT):0;
						long whitespaceCount = concurrentHashMap.get(ApplicationConstants.WHITE_SPACE)!=null?concurrentHashMap.get(ApplicationConstants.WHITE_SPACE):0;
						LOGGER.info("Successfully completed " + fileDetails.getFileName() + " with whitespaceCount "
								+ whitespaceCount + " count of dot " + countDot + " count of Word " + countWord
								+ " max repeated word : " + dataExtractor.keyWithMaxCount());

					} catch (Exception e) {
						LOGGER.error("Exception occurred while processing", e);
					}

				}
				key.reset();
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred while watching the directory", e);
		}

	}
}
