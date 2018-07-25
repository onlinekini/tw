package tw.gltc.shpng.service;

import java.math.BigDecimal;
import java.util.Arrays;

import tw.gltc.shpng.dto.ItemDTO;
import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.exception.ItemException;
import tw.gltc.shpng.ref.ItemRef;

public class ItemValueComputer {

	private SymbolToNumberConverter symbolConverter = null;
	private ItemRef itmRef = null;
	
	public ItemValueComputer (SymbolToNumberConverter symbolNumConverter, ItemRef itmRef) {
		this.symbolConverter = symbolNumConverter;
		this.itmRef = itmRef;
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
			int mathematicalValue = 0;
			if (requestDTO.hasItem()) {
				
				mathematicalValue = symbolConverter.convertToNumber(requestDTO.getSymbolArray());
				BigDecimal itemValue = itmRef.getItemValue(requestDTO.getAssociatedItem().getItemName().toUpperCase());
				requestDTO.setTotalPrice(itemValue.multiply(new BigDecimal(mathematicalValue)));
			} else {
				mathematicalValue = symbolConverter.convertToNumber(requestDTO.getSymbolArray());
				requestDTO.setTotalPrice(new BigDecimal(mathematicalValue));
			}
			
			return requestDTO;
		} else {
			throw new ConversionException("Request null, cannot convert");
		}
		
	}
	
	public ItemDTO calculateItemValue(String symbolsWithItemAtEnd, BigDecimal totalValue) throws ConversionException, ItemException {
		ItemDTO itemDto = null;
		if (symbolsWithItemAtEnd != null) {
			String[] symbolArray = symbolsWithItemAtEnd.split("\\s* \\s*");
			if (itmRef.containsItem(symbolArray[symbolArray.length -1])) {
				itemDto = new ItemDTO();
				itemDto.setItemName(symbolArray[symbolArray.length -1]);
				int mathematicalValue = symbolConverter.convertToNumber(Arrays.copyOf(symbolArray, symbolArray.length -1));
				BigDecimal itemValue = totalValue.divide(new BigDecimal(mathematicalValue));
				itemDto.setItemUnitValue(itemValue);	
			} else {
				return null;
			}
			return itemDto;
		} else {
			throw new ConversionException("Request null, cannot convert");
		}
		
	}
	
	
	private RequestDTO createRequestDTO(String inRequest) throws ItemException {
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setOriginalRequest(inRequest);
		String[] itemsAndNumerics = inRequest.split(" ");
		ItemDTO item = itmRef.getItem(itemsAndNumerics[itemsAndNumerics.length - 1]); // get the last item
		if (item != null) {
			reqDTO.setAssociatedItem(item);
			reqDTO.setHasItem(true);
			reqDTO.setSymbolArray(Arrays.copyOf(itemsAndNumerics, itemsAndNumerics.length - 1)); // truncated by one
		} else {
			// Else it is assumed it is a valid symbol
			reqDTO.setSymbolArray(itemsAndNumerics);
		}
		return reqDTO;
	}
	
}