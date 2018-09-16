package tw.mrchnt.guide.rules;

import java.util.ArrayList;
import java.util.List;

import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.message.MessageIfc;

public class NoItemWithSymbolRule implements MessageRuleIfc {

	private static NoItemWithSymbolRule rule;
	
	public static MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new NoItemWithSymbolRule();
		}
		return rule;
	}
	
	private NoItemWithSymbolRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		System.out.println("Applying : " + this.getClass().getName());
		String symbolStr = message.getDecomposedMessage()[0];
		SymbolRefCatalogue.getSymbolCatalogue().addSymbol(new MetaSymbol(symbolStr, message.getDecomposedMessage()[2]));
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		boolean canApplyRule = BaseMessageRules.endsWithCredit(message)&&
								BaseMessageRules.secondStringIS(message);
		
		return canApplyRule && checkZeroItem(message);
	}
	
	private boolean checkZeroItem(MessageIfc message) {
		int tempCount = 0;
		if (!"Credit".equalsIgnoreCase(message.getDecomposedMessage()[2])) {
			return false;
		}
		for (String possibleItem : message.getDecomposedMessage()) {
			if(ItemRefCatalogue.getItemRefCatalogue().isItem(possibleItem)) {
				tempCount++;
			}
		}
		return tempCount == 0;
	}

}
