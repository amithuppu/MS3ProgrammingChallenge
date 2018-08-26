# MS3ProgrammingChallenge

This is a programming challenge to parse the CSV file and store the records in Sqlite DB.  
For this program to run , you need to download and install the sqlite DB. I used Eclipse IDE to run the program. Open the file in Eclipse IDE and change the following paths : DB path, CSV file path, bad-data file path and successlog path. Execute the program and check the Database for the records. Check the bad-data file and Successlog. 

APPROACH:
1) Connect to DB and create a table if one deos not exist. 
2) Read the csv file line by line and check the values in each line. 
3) Check for the correctness of the values and if it equals to the number of columns then insert or else output to the bad-data file. 
