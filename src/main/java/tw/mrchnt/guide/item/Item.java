package tw.mrchnt.guide.item;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tw.mrchnt.guide.symbol.Symbols;

public class Item {

	private MetaItem referenceItem;
	private Symbols symbols;


	public Item(MetaItem itemName, Symbols symbols) {
		
	}
	
	public MetaItem getReferenceItem() {
		return referenceItem;
	}
	public void setReferenceItem(MetaItem referenceItem) {
		this.referenceItem = referenceItem;
	}
	public double getNumberOfItems() {
		return symbols.getTotalValue();
	}
	
	public String getItemCount() {
		return new BigDecimal(symbols.getTotalValue()).setScale(0, RoundingMode.DOWN).toString();
	}
	
	public Symbols getSymbols() {
		return symbols;
	}

	public void setSymbols(Symbols symbols) {
		this.symbols = symbols;
	}
}
