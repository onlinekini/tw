package tw.gltc.shpng.dto;

import java.math.BigDecimal;

public class ItemDTO implements Cloneable {

	private String itemName;
	private BigDecimal itemUnitPrice;
	
	public ItemDTO(String itmName, BigDecimal itmUnitPrice) {
		itemName = itmName;
		itemUnitPrice = itmUnitPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitValue(BigDecimal itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	@Override
	public ItemDTO clone() throws CloneNotSupportedException {
		return (ItemDTO) super.clone();
	}

	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ItemDTO)) {
			return false;
		}

		ItemDTO c = (ItemDTO) o;

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
