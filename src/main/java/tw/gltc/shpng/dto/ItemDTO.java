package tw.gltc.shpng.dto;

import java.math.BigDecimal;

public class ItemDTO {

	private String itemName;
	private BigDecimal itemUnitPrice;
	
	public ItemDTO() {
	
	}
	
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
	
}
