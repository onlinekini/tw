package tw.gltc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tw.mrchnt.guide.message.Message;
import tw.mrchnt.guide.message.SimpleMessageDecomposer;
import tw.mrchnt.guide.rules.DummyRule;
import tw.mrchnt.guide.rules.ItemValueAssignmentRule;
import tw.mrchnt.guide.rules.MessageRuleIfc;
import tw.mrchnt.guide.rules.SymbolValueAssignmentRule;
import tw.mrchnt.guide.rules.SymbolWithoutItemRule;
import tw.mrchnt.guide.rules.SingleItemWithCreditRule;

public class CoreTester {
	
	static List<MessageRuleIfc> messageRules;
	
	@BeforeClass
	public static void initAll() {
		messageRules = new ArrayList<>();
		messageRules.add(SymbolValueAssignmentRule.getRule()); // Get the symbol setup. This shold be first
		messageRules.add(SymbolWithoutItemRule.getRule());// simply comput the symbols' value
		messageRules.add(ItemValueAssignmentRule.getRule()); // Get the item setup
		messageRules.add(SingleItemWithCreditRule.getRule()); // Credit setup
		messageRules.add(DummyRule.getRule());//this should be the last 
	}

	@Test
	public void processInput() {
		System.out.println("Reading input file ... ");
		File inputFile = new File("Input.test");
		BufferedReader rdr = null;
		String input = null;
		try {
			rdr = new BufferedReader(new FileReader(inputFile));
			while ((input = rdr.readLine()) != null) {
				if (input.startsWith("#")) {
					continue; // This is to ignore comments
				}	
				// from here on it is a NON comment
				input = input.trim().replaceAll("( )+", " "); // remove multiple spaces if any.
				Message msg = new Message(input, new SimpleMessageDecomposer(" "), null);
				//System.out.println(msg.toString());
				for (MessageRuleIfc messageRule : messageRules) {
					if(messageRule.canApplyRule(msg)) {
						messageRule.applyRule(msg);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rdr != null) {
				try {
					rdr.close();
				} catch (IOException e) {
					// DO nothing for now
				}
			}
		}
	}
}
