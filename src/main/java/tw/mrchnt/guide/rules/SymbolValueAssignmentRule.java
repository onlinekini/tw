package tw.mrchnt.guide.rules;

import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.message.MessageIfc;

public class SymbolValueAssignmentRule implements MessageRuleIfc {

	private static SymbolValueAssignmentRule rule;
	
	public static MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new SymbolValueAssignmentRule();
		}
		return rule;
	}
	
	private SymbolValueAssignmentRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		//System.out.println("Applying : " + this.getClass().getName());
		String symbolStr = message.getDecomposedMessage()[0];
		SymbolRefCatalogue.getSymbolCatalogue().addSymbol(new MetaSymbol(symbolStr, message.getDecomposedMessage()[2]));
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		boolean canApplyRule = BaseMessageRules.endsWithRomanNumeral(message)&&
								BaseMessageRules.secondStringIS(message);
		//System.out.println(" Symbol setup : " + canApplyRule + " -> " + checkZeroItem(message));
		
		return canApplyRule && checkZeroItem(message);
	}
	
	private boolean checkZeroItem(MessageIfc message) {
		int tempCount = 0;
		for (String possibleItem : message.getDecomposedMessage()) {
			if(ItemRefCatalogue.getItemRefCatalogue().isItem(possibleItem)) {
				tempCount++;
			}
		}
		return tempCount == 0;
	}

}
