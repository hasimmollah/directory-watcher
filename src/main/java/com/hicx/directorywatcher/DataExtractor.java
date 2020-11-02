package com.hicx.directorywatcher;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataExtractor {

	private final ConcurrentHashMap<String, Long> wordCountMap;

	DataExtractor(final ConcurrentHashMap<String, Long> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}

	public void extractData(String sentense) {
		String[] splittedWords = sentense.split(ApplicationConstants.WHITE_SPACE_DELEMETER);
		Long countOfWhiteSpace = wordCountMap.get(ApplicationConstants.WHITE_SPACE);
		Long countOfWord = wordCountMap.get(ApplicationConstants.TOTAL_WORD);
		
		long wordLength = splittedWords.length;
		wordCountMap.put(ApplicationConstants.WHITE_SPACE,
				countOfWhiteSpace != null ? (countOfWhiteSpace + wordLength - 1) : wordLength - 1);
		
		
		wordCountMap.put(ApplicationConstants.TOTAL_WORD,
				countOfWord != null ? (countOfWord + wordLength) : wordLength);

		Arrays.stream(splittedWords).forEach(word -> {
			Long wordCount = wordCountMap.get(word);
			wordCount = wordCount != null ? wordCount + 1 : 1;
			wordCountMap.put(word, wordCount);

		});

	}

	public String keyWithMaxCount() {

		return wordCountMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
	}

}
