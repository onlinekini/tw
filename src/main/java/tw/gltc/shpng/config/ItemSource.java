package tw.gltc.shpng.config;

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
public class ItemSource {

	private static List<String> itemNames;
	private static Map<String, BigDecimal> itemValues = new HashMap<>(); //TODO change it to hold item DTOs
	
	public static void init() throws ItemException {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("itemSrc.properties")));
			itemNames = new ArrayList<>();
			itemNames.addAll(Arrays.asList(props.getProperty("item.names").trim().toUpperCase().split("\\s*,\\s*")));	
			System.out.println("Item init complete");
		} catch (FileNotFoundException e) {
			System.out.println(" File Not found : I cannot identify the source, hence cannot convert to human decimal numbers");
			throw new ItemException("File not found, cannot start without that" , e);
		} catch (IOException e) {
			System.out.println(" Other IO Exception: I cannot identify the source, hence cannot convert to human decimal numbers");
			throw new ItemException("Error reading source file, cannot start without that" , e);
		}
	}
	
	
	public static boolean checkItemExists(String item) throws ItemException {
		if (itemNames == null) {
			init();
		}
		
		return itemNames.contains(item.toUpperCase());
	}
	
	public static void updateItemValues(Map<String, BigDecimal> itemVals) throws ItemException {
		for (Map.Entry<String, BigDecimal> entry : itemVals.entrySet()) {
			if (checkItemExists(entry.getKey())) {
				updateItemValue(entry.getKey(), entry.getValue());
			} else {
				throw new ItemException("Item " + entry.getKey() + " not found ");
			}
		}
		
	}
	
	public static void updateItemValue(String item, BigDecimal itemValue) throws ItemException {
		itemValues.put(item, itemValue);
	}
	
	public static BigDecimal  getItemValue(String item) throws ItemException {
		BigDecimal output = checkItemExists(item) ? itemValues.get(item) : new BigDecimal(0) ;
		return output;
	}
	
	
	public static void main(String [] args) {
		try {
			ItemSource.init();
			System.out.println(itemNames);
		} catch (ItemException e) {
			e.printStackTrace();
		}
		
	}
}
