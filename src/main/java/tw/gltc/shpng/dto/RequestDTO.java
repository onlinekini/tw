package tw.gltc.shpng.dto;

import java.math.BigDecimal;

public class RequestDTO {

	private String originalRequest;
	private String associatedItem;
	private String[] numericArray;
	private String numeric; // This is not used for now
	private boolean hasItem;
	private boolean hasMoreItems; // Not used 
	private BigDecimal totalPrice;
	
	
	public String getOriginalRequest() {
		return originalRequest;
	}
	public void setOriginalRequest(String originalRequest) {
		this.originalRequest = originalRequest;
	}
	public String getAssociatedItem() {
		return associatedItem;
	}
	public void setAssociatedItem(String associatedItem) {
		this.associatedItem = associatedItem;
	}
	public boolean isHasItem() {
		return hasItem;
	}
	public void setHasItem(boolean hasItem) {
		this.hasItem = hasItem;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice.setScale(2);
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String[] getNumericArray() {
		return numericArray;
	}
	public void setNumericArray(String[] numericArray) {
		this.numericArray = numericArray;
	}
	
	
}
