### This app parses JMeter csv test log and provides some statistics data.

For now there only 4 methods available:
- printErrorStatisticsTotal
- printErrorStatisticsByEachThread
- printErrorStatisticsByThreadName
- printErrorStatisticsByURL

The new features maybe added in the future.

----
This app can write output to the Console or to File if you provide a path to it in program arguments.

To run the program you should run the Main class in your IDE or compile and then run it in the CLI mode. 

You have to set parameters such as path to JMeter csv file, path to the output file (optional), part of the name of invoke method like "total" or "thread" or "url" (optional), and number to limit the output lines for example 5 for top 5 errors or 10 for top 10 threads.

Example for CLI command to run the program after compilation:  
***java Main /Users/user/Documents/all.csv /Users/user/Documents/output.csv url 10***