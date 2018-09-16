package tw.mrchnt.guide.rules;

import java.util.ArrayList;
import java.util.List;

import tw.mrchnt.guide.cart.Symbols;
import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaItem;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.message.MessageIfc;

public class ItemValueAssignmentRule implements MessageRuleIfc {

	private static ItemValueAssignmentRule rule;
	
	public static MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new ItemValueAssignmentRule();
		}
		return rule;
	}
	
	private ItemValueAssignmentRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		System.out.println("Applying : " + this.getClass().getName());
		List<MetaSymbol> symbols = new ArrayList<>();
		String itemName = null;
		Double numericValueInMessage = null;
		for (String possibleSymbolItem : message.getDecomposedMessage()) {
			// Check item or Symbol
			if(SymbolRefCatalogue.getSymbolCatalogue().isSymbolPresent(possibleSymbolItem)) {
				symbols.add(SymbolRefCatalogue.getSymbolCatalogue().getMetaSymbol(possibleSymbolItem));
			} else if (ItemRefCatalogue.getItemRefCatalogue().isItem(possibleSymbolItem)) {
				itemName = possibleSymbolItem;
			} else if (isNumericValue(possibleSymbolItem)) {
				numericValueInMessage = Double.parseDouble(possibleSymbolItem);
			}
		}
		MetaItem metaItm = ItemRefCatalogue.getItemRefCatalogue().getItem(itemName);
			
		Symbols symbs = new Symbols(symbols, new RomanNumeralRule());
		metaItm.setItemUnitPrice(numericValueInMessage / symbs.getTotalValue());
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		boolean canApplyRule = BaseMessageRules.endsWithCredit(message) && BaseMessageRules.containsNumber(message);
		
		return canApplyRule && checkOneItem(message);
	}
	
	private boolean checkOneItem(MessageIfc message) {
		int tempCount = 0;
		for (String possibleItem : message.getDecomposedMessage()) {
			if(ItemRefCatalogue.getItemRefCatalogue().isItem(possibleItem)) {
				tempCount++;
			}
		}
		return tempCount == 1; // is it equal to ONE ?
	}

	private boolean isNumericValue(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
	
}
