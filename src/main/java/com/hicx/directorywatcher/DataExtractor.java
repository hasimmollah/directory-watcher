package com.hicx.directorywatcher;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DataExtractor {

	private final ConcurrentHashMap<String, Long> wordCountMap;

	DataExtractor(final ConcurrentHashMap<String, Long> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}

	public void extractData(String sentense) {
		String[] splittedWords = sentense.split(ApplicationConstants.WHITE_SPACE_DELEMETER);
		Long countOfWhiteSpace = wordCountMap.get(ApplicationConstants.WHITE_SPACE);
		Long totalCountOfWord = wordCountMap.get(ApplicationConstants.TOTAL_WORD);

		final long wordLength = splittedWords.length;
		wordCountMap.put(ApplicationConstants.WHITE_SPACE, Optional.ofNullable(countOfWhiteSpace)
				.map(whiteSpaceCount -> countOfWhiteSpace + wordLength - 1).orElse(wordLength - 1));

		wordCountMap.put(ApplicationConstants.TOTAL_WORD, Optional.ofNullable(totalCountOfWord)
				.map(totalWordCount -> totalWordCount + wordLength).orElse(wordLength));

		Arrays.stream(splittedWords).parallel().forEach(word -> {
			Long wordCount = wordCountMap.get(word);
			wordCountMap.put(word,
					Optional.ofNullable(wordCount).map(countOfWord -> countOfWord + 1).orElse(1L));

		});
		splittedWords = null;
		sentense =null;
	}

	public String keyWithMaxCount() {

		return wordCountMap.entrySet().stream().parallel()
				.filter(entry -> !(ApplicationConstants.WHITE_SPACE.equalsIgnoreCase(entry.getKey())
						|| ApplicationConstants.TOTAL_WORD.equalsIgnoreCase(entry.getKey())
						|| ApplicationConstants.DOT.equalsIgnoreCase(entry.getKey())
						|| ApplicationConstants.EMPTY_STR.equalsIgnoreCase(entry.getKey())))
				.max(Map.Entry.comparingByValue()).get().getKey();
	}
}
