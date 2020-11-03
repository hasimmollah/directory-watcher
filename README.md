## directory-watcher
Purpose of this application is to watch a directory (location is specified as input from user) for any new file is inserted.
once a new file is inserted, application reads the file
#### find following:
	- The number of whitespace 
	- Total number of words
	- Number of DOT (.)
	- The word repeated most.
###### Once file has been processed, it's moved to a directory - <<user home directory>>/processed
###### It abled to process 10.5 GB file in 1.5 mins

###### As design of this application involved lots of string handling, have to use following JVM parameter for better memory utilization
 - -XX:+UseG1GC : to use G1 GC
 - -XX:+UseStringDeduplication : to reuse same string literal as char array.
