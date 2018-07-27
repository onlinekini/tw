###############
# This project uses maven only for running the tests.
# The jar created will not run from command line.
# Tested only on eclipse. (though it should not matter)
# Starting class for review is : IntergalacticMerchantSvc.java
###############

# Notations
Gold, Silver etc are items
glob, pish etc are symbols
the line which provides the value of symbols is setup information
the line which provides the value of items (item comutation formula) is also setup information
the comutation requirement is considered as a request. Classes, methods etc are named accordingly

# Assumptions and requirements for the application to run
#Assumption 1
Symbols are single words. 
The "glob" would Symbol, glob_phish would be a single symbol, glob glob are two symbols. They cannot be single symbol with a space in between

#Assumption 2  
"is", "Credit", "?" are a keyword (without "" marks)

#Assumption 3
All questions which need to be computed should should end with "?", without which the application would fail

# Assumption 4
item names should be present in items.properties, else may fail. .. This I Did not test yet!

#Assumption 5
system will not do double pass compiling for source. 
So setup/ reference data like symbol value or item computation formula should be present before any "request" is present.
for Example : 
	symbol equivalent info should be present before computing value of item
	item value should be present before computing merchant's selling price

#Assumption 6	
Program is all or none. if one of the input fails with a runtimeexception, all future computations will not be performed

#Assumption 7
Comments should start with #, but lines should have comments after valid information, else it would fail in parsing with garbage output

#Assumption 8
Did not check Wikipedia reference for roman nueral computation. if there are more conditions in Wiki, I will work on them over the next two days and submit.
