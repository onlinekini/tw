package tw.mrchnt.guide.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tw.mrchnt.guide.cart.Symbols;
import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.message.MessageIfc;

public class SymbolWithoutItemRule implements MessageRuleIfc {

	private static SymbolWithoutItemRule rule;
	
	public static MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new SymbolWithoutItemRule();
		}
		return rule;
	}
	
	private SymbolWithoutItemRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		//System.out.println("Applying : " + this.getClass().getName());
		List<MetaSymbol> metas = new ArrayList<>();
		for (String possibleSymbol : message.getDecomposedMessage()) {
			// Check item or Symbol
			if(SymbolRefCatalogue.getSymbolCatalogue().isSymbolPresent(possibleSymbol)) {
				metas.add(SymbolRefCatalogue.getSymbolCatalogue().getMetaSymbol(possibleSymbol));
			}
		}			
		Collections.reverse(metas);
		Symbols symbs = new Symbols(metas, new RomanNumeralRule());
		System.out.println(symbs + " is "+ symbs.getTotalValueString());
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		boolean canApplyRule = BaseMessageRules.startsWithHowMuch(message)&&
								BaseMessageRules.thirdStringIS(message);
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
