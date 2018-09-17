# Generic Information
* This project uses maven only for running the tests.
* The jar created will not run from command line.
* Tested only on eclipse. (though it should not matter)
* Starting test class CoreTester.java
* Input to the application is from input.test 
* Program is Case agnostic. is NOT case sensitive.
* Any Change in sentence will need a new Message processing rule (check section : *Important Class Names and Details* for more info on rules) 

# Details
 
## Setup:
* `item.properties` : This sets up item info. If you want to add more items - add it to the comma separated list without spaces
* `setup.properties` : This is the config setup for the application. Do not modify anything unless you know what you are doing. Wrong config will cause the program to fail

## Important Class Names and details
* All IFCs are interfaces. (Generally I dont use IFC, used here for clarity against similar sounding names). 
* **Package** `tw.mrchnt.guide.message`:
	* `Message` - This represents a line from input file (input.test)
	* `SimpleMessageDecomposer` - Decomposer implementation fed to the Message. This decomposes the messages into message composenents for pasring. it can handle any types word separators like #... etc (currently " " <space>)
	* `PrepperIfc` implementations are provided just in case the message needs to be prepped before sending for processing, like removing unnecessary characters in the lines. NOT USED CURRENTLY.
* **Package** `tw.mrchnt.guide.config`:
	* `MetaItem`: This holds A Meta Item. A META Item is a setup info. Like Iron, Gold etc. Whose unit price can be modified.
	* `ItemRefCatalog` : This holds a catalogue of META item (s). 
	* `MetaSymbol` : Holds the Symbol and its Roman and Numeric equivalent value. like hold Glob, its Roman value say I and its numeric value = 1
	* `SymbolRefCatalog` : Holds a catalogue of META Symbol(s)
* **Package** `tw.mrchnt.guide.cart`:
	* `Symbols`: Symbols is a group of meta symbols : like Glob glob which is present in the input. 
  	* `Item` : Cart- Item holds the full message equivalent of an item. Example : glob glob Gold is an Item. Which is Symbols & Item
* **Package** `tw.mrchnt.guide.rules`
	* `RomanNumeralRule` implements `NumeralRuleIfc` : Basically has all the rules for Processing roman numerals.
	### Message processing Rules.
	* `BaseMessageRules`: Helper class with all basic rules for processing a Message 
	* `ItemValueAssignmentRule` : Processes the sentence `glob glob Silver is 34 Credits` to assign the value of a META item
	* `SingleItemWithCreditRule` : Processes the sentence `how many Credits is glob prok Silver ?` or similar sentences. Providing total Credit value
	* `SymbolValueAssignmentRule` : Processes the sentence `tegj is L` to compute the META symbol value. 
	* `SymbolWithoutItemRule` : Processes the sentence `how much is pish tegj glob glob ?` . Just computes the value of Symbols provided.
	* `DummyRule` implemts `MessageRuleIfc` : catch all, when no other rule works. This should ALWAYS BE CHECKED LAST in the list. output: " I dont know what you are talking about" 
	
	
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
