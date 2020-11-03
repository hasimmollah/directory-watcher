## directory-watcher
Purpose of this application is to watch a directory (location is specified as input from user) for any new file is inserted.
once a new file is inserted, application reads the file
#### Find out following:
	- The number of whitespace 
	- Total number of words
	- Number of DOT (.)
	- The word repeated most.
###### Once file has been processed, it's moved to a directory - << user home directory >>/processed
###### It is abled to process 10.5 GB file in 1.5 mins

###### As design of this application involved lots of string handling, have to use following JVM parameter for better memory utilization
 - -Xms2048m : To specify initial memory
 - -XX:+UseG1GC : To use G1 Garbage Collection
 - -XX:+UseStringDeduplication : To remove duplicate same string literal, jvm removes underlying char []
