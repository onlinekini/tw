package tw.gltc.shpng.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import tw.gltc.shpng.dto.ItemDTO;
import tw.gltc.shpng.exception.ItemException;

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
public class ItemRef {

	private List<String> itemNames;
	private Map<String, ItemDTO> itemValues = new HashMap<>();
	private static ItemRef itmRef;
	
	private ItemRef() {
		try {
			init();
		} catch (ItemException e) {
			System.out.println("Cannot create itemRef");
		}
	}
	
	public static ItemRef getInstance() {
		if(itmRef == null) {
			itmRef = new ItemRef();
		};
		return itmRef;
	}
	
	private void init() throws ItemException {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("items.properties")));
			itemNames = new ArrayList<>();
			itemNames.addAll(Arrays.asList(props.getProperty("item.names").trim().toUpperCase().split("\\s*,\\s*")));	
			//System.out.println("Item init complete");
		} catch (FileNotFoundException e) {
			System.out.println(" File Not found : I cannot identify the source, hence cannot convert to human decimal numbers");
			throw new ItemException("File not found, cannot start without that" , e);
		} catch (IOException e) {
			System.out.println(" Other IO Exception: I cannot identify the source, hence cannot convert to human decimal numbers");
			throw new ItemException("Error reading source file, cannot start without that" , e);
		}
	}
	
	
	public ItemDTO getItem(String item) throws ItemException {
		return itemValues.get(item.toUpperCase());
	}
	
	public boolean containsItem(String item) throws ItemException {
		return itemNames.contains(item.toUpperCase());
	}
	
	public void updateItemValues(Map<String, BigDecimal> itemVals) throws ItemException {
		for (Map.Entry<String, BigDecimal> entry : itemVals.entrySet()) {
			if (itemNames.contains(entry.getKey())) {
				updateItemValue(entry.getKey(), entry.getValue());
			} else {
				throw new ItemException("Item " + entry.getKey() + " not found ");
			}
		}
		
	}
	
	public void updateItemValue(String itemName, BigDecimal itemValue) throws ItemException {
		itemNames.add(itemName.toUpperCase());
		itemValues.put(itemName.toUpperCase(), new ItemDTO(itemName.toUpperCase(), itemValue));
	}
	
	public void updateItemValue(String itemName, ItemDTO item) throws ItemException {
		itemValues.put(itemName.toUpperCase(), item);
	}
	
	public BigDecimal getItemValue(String item) throws ItemException {
		BigDecimal itemValue = itemValues.get(item) != null ? itemValues.get(item).getItemUnitPrice() : new BigDecimal(0);
		return itemValue;
	}
	
	
	public List<String> getItemNames() {
		return itemNames;
	}

	public Map<String, ItemDTO> getItemValues() {
		return itemValues;
	}

	public ItemRef getItmRef() {
		return itmRef;
	}

	public static void main(String [] args) {
		ItemRef itmRf = new ItemRef();
		System.out.println(itmRf.getItemNames());
	}
}
