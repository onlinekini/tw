package tw.gltc.shpng.ref.item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import tw.gltc.shpng.dto.ItemDTO;

/**
 * This is a bean holding all the conversion values.
 * The bean expects that the file "source.properties" exists and is readable in the classpath. 
 * 
 * The file should contain the keys as the galatic numerals and the value should be mathematical numbers (not roman numerals)
 * The keys will be considered strings and will NOT be case sensitive. 
 * 
 * 
 * You will find the same message in source.properties
 * 
 * @author vkini
 *
 */
public interface ItemRefIfc {
	
	/**
	 * Get an item given item name
	 * 
	 * @param itemName : item name of the item to be fetched
	 * @return {@link ItemDTO}. Inem information
	 */
	public ItemDTO getItem(String itemName);
	
	
	/**
	 * Checks if the item name is present in the list of items
	 * 
	 * @param itemName : name of the item to be checked in the list of items 
	 * @return boolean true if item present.
	 */
	public boolean containsItem(String itemName);
	
	
	/**
	 * Update a set of items, with their values.
	 * 
	 * @param itemVals a Map <Sting, BigDecimal> which signifies the item name and its decimal value if any
	 */
	public void updateItemValues(Map<String, BigDecimal> itemVals);
	
	/**
	 * Update item Value of a single item. Create it if it does not exist
	 * 
	 * @param itemName : Name of the item
	 * @param itemValue : bigdecimal Value of the item
	 */
	public void updateItemValue(String itemName, BigDecimal itemUnitValue);
	
	/**
	 * Update an item, given the item DTO. 
	 * Helper method if someone wants to create the item with the DTO iteslf.
	 * 
	 * @param item ItemDTO object
	 */
	public void updateItemValue(ItemDTO item);
	
	/**
	 * get the item Value given itemName.
	 * 
	 * @param itemName. Item name String 
	 * @return item value in bigDecimal format
	 */
	public BigDecimal getItemValue(String itemName);
	
	/**
	 * Get all item names.
	 * @return List of item names
	 */
	public List<String> getItemNames();
}
