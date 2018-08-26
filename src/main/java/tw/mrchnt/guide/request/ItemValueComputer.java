package tw.mrchnt.guide.request;

import java.math.BigDecimal;
import java.util.Arrays;

import tw.gltc.shpng.dto.Item;
import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ComputeException;
import tw.gltc.shpng.ref.item.GalacticItemRef;
import tw.gltc.shpng.ref.item.ItemRefIfc;
import tw.gltc.shpng.symbol.RomanToNumConverter;

/**
 * This class does the computation of a "requirement" into a value. provided the requirments have valid symbols and items
 * 
 * Uses {@link GalacticItemRef} as the source of all item information
 * Uses {@link GalaticToNumric} to convert symbols to numeric values
 * These are constructor arguments.
 *  
 * @author vkini
 *
 */
public class ItemValueComputer {

	private GalaticToNumric symbolConverter = null;
	private ItemRefIfc itmRef = null;
	
	/**
	 * Constructor
	 * 
	 * @param symbolNumConverter {@link GalaticToNumric}
	 * @param itmRef {@link GalaticToNumric}
	 */
	public ItemValueComputer (GalaticToNumric symbolNumConverter, ItemRefIfc itmRef) {
		this.symbolConverter = symbolNumConverter;
		this.itmRef = itmRef;
	}
	
	
	/**
	 * This method takes in the symbol input along with the item and then converts it to an objects with all the information of the request
	 * It strips the item, computes the mathematic value of the symbols and finally multiplies it with the unit price of the item.
	 * 
	 * Else throws Runtime exception {@link ComputeException} is the symbol or item is null or invalid
	 * 
	 * @param inRequest String format of the requirement
	 * @return the entire information of the request alogn with original Request String and th item associated with the string {@link RequestDTO}
	 * 
	 */
	public RequestDTO getTotalValue(String inRequest) {
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
			throw new ComputeException("Request null, cannot convert");
		}
		
	}
	
	/**
	 * This method computes the unit value of the item, given the "formula" and the value.
	 * Provided the symbols and the items are valid
	 * 
	 * Else throws Runtime exception {@link ComputeException} is the symbol is null or invalid
	 * 
	 * @param symbolsWithItemAtEnd request string with symbols and item
	 * @param totalValue value of the formula provided in the previous argument 
	 * @return Item information with item name and item unit price {@link MessageRuleIfc}
	 */
	public MessageRuleIfc calculateItemValue(String symbolsWithItemAtEnd, BigDecimal totalValue) {
		MessageRuleIfc itemDto = null;
		if (symbolsWithItemAtEnd != null) {
			String[] symbolArray = symbolsWithItemAtEnd.split("\\s* \\s*");
			if (itmRef.containsItem(symbolArray[symbolArray.length -1].toUpperCase())) {
				int mathematicalValue = symbolConverter.convertToNumber(Arrays.copyOf(symbolArray, symbolArray.length -1));
				BigDecimal itemValue = totalValue.divide(new BigDecimal(mathematicalValue));
				itemDto = new MessageRuleIfc(symbolArray[symbolArray.length -1].toUpperCase(), itemValue);
			} else {
				return null;
			}
			return itemDto;
		} else {
			throw new ComputeException("Request null, cannot convert");
		}
		
	}
	
	/*
	 * Create a RequestDTO from the incoming string request
	 */
	private RequestDTO createRequestDTO(String inRequest) {
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setOriginalRequest(inRequest);
		String[] itemsAndNumerics = inRequest.split(" ");
		MessageRuleIfc item = itmRef.getItem(itemsAndNumerics[itemsAndNumerics.length - 1]); // get the last item
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