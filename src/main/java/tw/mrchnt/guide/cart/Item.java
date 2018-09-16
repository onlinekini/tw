package tw.mrchnt.guide.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tw.mrchnt.guide.config.MetaItem;

/**
 * This class represents the line .. glob glob gold.
 * where glob glob is within Symbols and gold is the meta item info 
 *
 */
public class Item {

	private MetaItem referenceItem;
	private Symbols symbols;

	public Item(MetaItem item, Symbols smbls) {
		referenceItem = item;
		symbols = smbls;
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
	
	public String getTotalItemValue() {
		return new BigDecimal(symbols.getTotalValue() * referenceItem.getItemUnitPrice()).setScale(0, RoundingMode.DOWN).toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder()
								.append(symbols.toString())
								.append(referenceItem.toString())
								.append(" is ")
								.append(getTotalItemValue())
								.append(" Credits");
		return sb.toString(); 
	}
}
