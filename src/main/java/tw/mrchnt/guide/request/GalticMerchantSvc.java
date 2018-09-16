package tw.mrchnt.guide.request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import tw.mrchnt.guide.message.Message;
import tw.mrchnt.guide.message.SimpleMessageDecomposer;
import tw.mrchnt.guide.rules.ItemValueAssignmentRule;
import tw.mrchnt.guide.rules.MessageRuleIfc;
import tw.mrchnt.guide.rules.SymbolValueAssignmentRule;
import tw.mrchnt.guide.rules.SingleItemWithCreditRule;

/**
 * This class is a helper to test the input from the Input.test file 
 * All the test cases which were provided in the requirement have been saved there.
 * 
 * This simply parses the input file and processes the line to idenfy if it is ... 
 * <ul>
 *   <li>symbol reference - source</li>
 *   <li>item reference - geting unit price of an item</li>
 *   <li>test conditions - Whatever the merchant wants to convert</li>
 *	</ul>
 *
 *	This class does not have a test case.
 * 
 * @author vkini
 *
 */
public class GalticMerchantSvc {
	
	private List<MessageRuleIfc> messageRules;

	
}
