package com.hicx.directorywatcher;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class WatchDirectoryApp {
	 final static Logger LOGGER = Logger.getLogger(WatchDirectoryApp.class);

	

	public static void main(String[] args) {
		DirectoryManager directoryManager = new DirectoryManager();
		Scanner in = new Scanner(System.in);
		LOGGER.info("Please enter the directory path to watch");
		String directoryPath = in.nextLine();

		directoryManager.watchDirectory(directoryPath);

	}

}
