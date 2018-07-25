package tw.gltc.shpng;

import java.math.BigDecimal;
import java.util.Arrays;

import tw.gltc.shpng.config.ItemSource;
import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.exception.ItemException;

public class ItemValueComputer {

	private CreditConverter creditConverter = null;
	
	
	public ItemValueComputer (CreditConverter inputCreditConverter) {
		this.creditConverter = inputCreditConverter;
	}
	
	
	/**
	 * This method takes in the Galactic numeral input along with the item and then converts the output 
	 * strips the item, computes the mathematic value of the numerals and finally multiplies it with the unit price of the item.
	 * 
	 * @param sourceCreditValue . Non null Numeral set. This this cas Galactic numerals
	 * @return mathematical (or decimal to be precise) equivalent of the galatic numerals
	 * @throws ItemException 
	 */
	public RequestDTO getTotalValue(String inRequest) throws ConversionException, ItemException {
		if (inRequest != null) {
			RequestDTO requestDTO = createRequestDTO(inRequest);
			BigDecimal itemValue = ItemSource.getItemValue(requestDTO.getAssociatedItem());
			int mathematicalValue = creditConverter.getMathematicalCreditValue(requestDTO.getNumericArray());
			requestDTO.setTotalPrice(itemValue.multiply(new BigDecimal(mathematicalValue)));
			return requestDTO;
		} else {
			throw new ConversionException("Request null, cannot convert");
		}
		
	}
	
	public BigDecimal getItemValue(String inRequestWithItem, BigDecimal totalValue) throws ConversionException, ItemException {
		if (inRequestWithItem != null) {
			RequestDTO requestDTO = createRequestDTO(inRequestWithItem);
			int mathematicalValue = creditConverter.getMathematicalCreditValue(requestDTO.getNumericArray());
			BigDecimal itemPrice = totalValue.divide(new BigDecimal(mathematicalValue));
			return itemPrice;
		} else {
			throw new ConversionException("Request null, cannot convert");
		}
		
	}
	
	
	
	/*
	 * Its assumed that the previous value is greater than current value
	 */
	private RequestDTO createRequestDTO(String inRequest) throws ItemException {
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setOriginalRequest(inRequest);
		String[] itemsAndNumerics = inRequest.split(" ");
				
		boolean isItem = false;
		isItem = ItemSource.checkItemExists(itemsAndNumerics[itemsAndNumerics.length - 1]); // get the last item
		if (isItem) {
			reqDTO.setHasItem(isItem);
			reqDTO.setNumericArray(Arrays.copyOf(itemsAndNumerics, itemsAndNumerics.length - 1)); // truncated by one
		} else {
			// Else it is assumed it is a valid numeric
			reqDTO.setNumericArray(itemsAndNumerics);
		}
		
		return reqDTO;
	}
	
}