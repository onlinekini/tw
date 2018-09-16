package tw.mrchnt.guide.config;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MetaItem implements Cloneable {

	private final String itemName;
	private final Double itemUnitPrice;
	
	public MetaItem(String itmName, Double itmUnitPrice) {
		itemName = itmName;
		itemUnitPrice = itmUnitPrice;
	}
	
	public MetaItem(String itmName) {
		itemName = itmName;
		itemUnitPrice = 1.0;
	}

	public String getItemName() {
		return itemName.toLowerCase();
	}

	public Double getItemUnitPrice() {
		return Double.valueOf(itemUnitPrice);
	}
	
	public Double getItemUnitPriceScaledTo(int scale) {
		return (new BigDecimal(itemUnitPrice).setScale(scale, RoundingMode.DOWN)).doubleValue();
	}

	@Override
	public MetaItem clone() throws CloneNotSupportedException {
		return (MetaItem) super.clone();
	}

	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof MetaItem)) {
			return false;
		}

		MetaItem c = (MetaItem) o;

		// Compare the data members and return accordingly
		return itemName.equals(c.itemName) && (itemUnitPrice.compareTo(c.itemUnitPrice) == 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode()) + ((itemUnitPrice == null) ? 0 : itemUnitPrice.hashCode());
		return result;
	}
}
