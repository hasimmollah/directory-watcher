package com.hicx.model;

public class ExtractedStat {
	
	String fileName;
	String mostRepeatedWord;
	long countOFDot;
	long countOFWhiteSpace;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMostRepeatedWord() {
		return mostRepeatedWord;
	}
	public void setMostRepeatedWord(String mostRepeatedWord) {
		this.mostRepeatedWord = mostRepeatedWord;
	}
	public long getCountOFDot() {
		return countOFDot;
	}
	public void setCountOFDot(long countOFDot) {
		this.countOFDot = countOFDot;
	}
	public long getCountOFWhiteSpace() {
		return countOFWhiteSpace;
	}
	public void setCountOFWhiteSpace(long countOFWhiteSpace) {
		this.countOFWhiteSpace = countOFWhiteSpace;
	}
	
	@Override
	public String toString() {
		return "File Name: " + this.fileName+ " Most Repeated Word " + mostRepeatedWord + " Count OF Dot" + countOFDot + " Count OF White Space" + countOFWhiteSpace;
	}

}
