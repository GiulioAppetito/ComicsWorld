#This script is specific to our project#
#it can eventually be adapted for your necessities, but it needs some fixing#

#README: use the python script to analyze report stats from github#


You may need:  pip install openpyxl
               pip install xlsxwriter
               pip install pandas
               pip install matplotlib


First of all you need to generate(or update) the "report.txt" file, containing
all the info you want to analyze, in our case it regards commits per month.
Run this command in the folder of the github project:
(in our case: C:\..\ComicsWorld) 

git log > report.txt


Then, run the script located in the same folder of the "report.txt" file.
(python script.py)

You will now have generated a "report.csv" file, containing two sheets:

-the first one illustrates every commit with its relative code, author and date

-the second one summarizes by month the number of commits per person, next to 
 the UpperControlLimit, Average and LowerControlLimit, and displays this info on
 a line-graph.