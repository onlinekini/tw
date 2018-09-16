package tw.mrchnt.guide.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tw.mrchnt.guide.cart.Item;
import tw.mrchnt.guide.cart.Symbols;
import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.message.MessageIfc;

public class SingleItemWithCreditRule implements MessageRuleIfc {

	private static SingleItemWithCreditRule rule;
	
	public static MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new SingleItemWithCreditRule();
		}
		return rule;
	}
	
	private SingleItemWithCreditRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		//System.out.println("Applying : " + this.getClass().getName());
		List<MetaSymbol> symbols = new ArrayList<>();
		String itemName = null;
		for (String possibleSymbolItem : message.getDecomposedMessage()) {
			// Check item or Symbol
			if(SymbolRefCatalogue.getSymbolCatalogue().isSymbolPresent(possibleSymbolItem)) {
				symbols.add(SymbolRefCatalogue.getSymbolCatalogue().getMetaSymbol(possibleSymbolItem));
			} else if (ItemRefCatalogue.getItemRefCatalogue().isItem(possibleSymbolItem)) {
				itemName = possibleSymbolItem;
			}
		}
		
		Collections.reverse(symbols);
		Item anItem = new Item(ItemRefCatalogue.getItemRefCatalogue().getItem(itemName), new Symbols(symbols, new RomanNumeralRule()));
		System.out.println(anItem.toString());
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		boolean canApplyRule = 	BaseMessageRules.startsWithHowMany(message);
		//System.out.println(" Symbol setup : " + canApplyRule + " -> " + checkOneItemWithCredit(message));
		
		return canApplyRule && checkOneItemWithCredit(message);
	}
	
	private boolean checkOneItemWithCredit(MessageIfc message) {
		int tempCount = 0;
		if (!"Credits".equalsIgnoreCase(message.getDecomposedMessage()[2])) {
			return false;
		}
		for (String possibleItem : message.getDecomposedMessage()) {
			if(ItemRefCatalogue.getItemRefCatalogue().isItem(possibleItem)) {
				tempCount++;
			}
		}
		return tempCount == 1;
	}

}
