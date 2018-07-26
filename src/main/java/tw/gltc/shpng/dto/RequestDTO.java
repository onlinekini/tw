package tw.gltc.shpng.dto;

import java.math.BigDecimal;

public class RequestDTO {

	private String originalRequest;
	private ItemDTO associatedItem;
	private String[] symbolArray;
	// private String symbol; // This is not used for now
	private boolean hasItem;
	// private boolean hasMoreItems; // Not used
	private BigDecimal totalPrice;

	public String getOriginalRequest() {
		return originalRequest;
	}

	public void setOriginalRequest(String originalRequest) {
		this.originalRequest = originalRequest;
	}

	public ItemDTO getAssociatedItem() {
		return associatedItem;
	}

	public void setAssociatedItem(ItemDTO associatedItem) {
		if (associatedItem != null) {
			setHasItem(true);
			this.associatedItem = associatedItem;
		}
	}

	public boolean hasItem() {
		return hasItem;
	}

	public void setHasItem(boolean hasItem) {
		this.hasItem = hasItem;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice.setScale(0);
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String[] getSymbolArray() {
		return symbolArray;
	}

	public void setSymbolArray(String[] symbolArr) {
		this.symbolArray = symbolArr;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof RequestDTO)) {
			return false;
		}

		RequestDTO c = (RequestDTO) o;

		// Compare the data members and return accordingly
		return originalRequest.equals(c.originalRequest) && (totalPrice.compareTo(c.totalPrice) == 0) && (hasItem == c.hasItem);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((originalRequest == null) ? 0 : originalRequest.hashCode()) + ((totalPrice == null) ? 0 : totalPrice.hashCode()) + (hasItem ? 0 : 1);
		return result;
	}
}
