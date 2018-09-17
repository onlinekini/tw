# This project uses maven only for running the tests.
# The jar created will not run from command line.
# Tested only on eclipse. (though it should not matter)
# Starting test class CoreTester.java
# Input to the application is from input.test 
# Program is Case agnostic. is NOT case sensitive.

## Setup:
* item.properties : This sets up item info. If you want to add more items - add it to the comma separated list without spaces
* setup.properties : This is the config setup for the application. Do not modify anything unless you know what you are doing. Wrong config will cause the program to fail

## Important Class Names and details
* All IFCs are interfaces. (Generally I dont use IFC, used here for clarity with similar sounding names. 
* tw.mrchnt.guide.message
	* Message - This represents a line from input file (input.test)
	* SimpleMessageDecomposer - Decomposer implementation fed to the Message. This decomposes the messages into message composenents for pasring. it can handle any types word separators like #,* etc (currently " " <space>)
	* Prepper implementations are provided just in case the message needs to be prepped before sending for processing, like removing unnecessary characters in the lines. NOT USED. Just for upgrades.
* tw.mrchnt.guide.config
	* ItemRefCatalog : This holds a catalogue of META item (look MetaItem below). 
	* MetaItem: This holds A Meta Item. A META Item is a setup info. Like Iron, Gold etc. Whose unit price can be modified.
	* SymbolRefCatalog : Holds a catalogue of META Symbol. (look for Symbols below)
	* MetaSymbol : Holds the Symbol and its Roman and Numeric equivalent value. like hold Glob, its Roman value say I and its numeric value = 1

	

## Notations
Gold, Silver etc are items
glob, pish etc are symbols; You may add other sysmbols as long as as you provide the roman numeral equivalent


## Assumptions and requirements for the application to run
## Assumptions 
* Symbols are single words. 
* The "glob" would Symbol, glob_phish would be a single symbol, glob glob are two symbols. They cannot be single symbol with a space in between
* "what", "how", "much", "is", "Credit", "Credits", "?" cannot represent item names
* item names should be present in items.properties, else may fail.
* system will not do double pass compiling for source. 
* So setup/ reference data like symbol value or item computation formula should be present before any "request" is present.
	for Example : 
		symbol equivalent info should be present before computing value of item
		item value should be present before computing merchant's selling price
* Program is all or none. if one of the input fails with a runtime exception, all futur
* Comments should start with #
