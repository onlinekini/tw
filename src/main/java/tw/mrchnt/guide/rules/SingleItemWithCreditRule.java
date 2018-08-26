package tw.mrchnt.guide.rules;

import java.util.ArrayList;
import java.util.List;

import tw.mrchnt.guide.config.ItemRef;
import tw.mrchnt.guide.config.SymbolRef;
import tw.mrchnt.guide.message.MessageIfc;

public class SingleItemWithCreditRule implements MessageRuleIfc {

	private static SingleItemWithCreditRule rule;
	
	public MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new SingleItemWithCreditRule();
		}
		return rule;
	}
	
	private SingleItemWithCreditRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		List<String> symbols = new ArrayList<>();
		for (String possibleSymbolItem : message.getDecomposedMessage()) {
			if(SymbolRef.getSymbolRef().isSymbol(possibleSymbolItem)) {
				symbols.add(possibleSymbolItem);
			} else if (ItemRef.getItemRef().isItem(possibleSymbolItem)) {
				
			}
		}
		
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		boolean canApplyRule = BaseMessageRules.endsWithQuestion(message) && 
								BaseMessageRules.startsWithHowMany(message) && 
								BaseMessageRules.fourthStringIs(message);
		
		return canApplyRule && checkOneItemWithCredit(message);
	}
	
	private boolean checkOneItemWithCredit(MessageIfc message) {
		int tempCount = 0;
		if (!"Credit".equals(message.getDecomposedMessage()[2])) {
			return false;
		}
		for (String possibleItem : message.getDecomposedMessage()) {
			if(ItemRef.getItemRef().isItem(possibleItem)) {
				tempCount++;
			}
		}
		return tempCount == 1;
	}

}
