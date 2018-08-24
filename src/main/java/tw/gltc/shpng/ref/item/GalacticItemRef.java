package tw.gltc.shpng.ref.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
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
public class GalacticItemRef implements ItemRefIfc {

	private Map<String, ItemDTO> items = new HashMap<>();
	private static ItemRefIfc itmRef;
	
	private GalacticItemRef() {
		try {
			init();
		} catch (ItemException e) {
			System.out.println("Cannot create itemRef");
		}
	}
	
	public static ItemRefIfc getInstance() {
		if(itmRef == null) {
			itmRef = new GalacticItemRef();
		};
		return itmRef;
	}
	
	private void init() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("items.properties")));
			
 			List<String> itemList = Arrays.asList(props.getProperty("item.names").trim().toUpperCase().split("\\s*,\\s*"));	
 			for (String itmName : itemList ) {
 				items.put(itmName.toUpperCase(), new ItemDTO(itmName.toUpperCase(),  new BigDecimal(0)));
 			}
 			
 			//System.out.println("Item init complete");
		} catch (FileNotFoundException e) {
			System.out.println(" File Not found : I cannot identify the items");
			throw new ItemException("items.properties File not found, cannot start without that" , e);
		} catch (IOException e) {
			System.out.println(" Cannot read file : I cannot identify the items");
			throw new ItemException("Error reading source file items.properties, cannot start without that" , e);
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#getItem(java.lang.String)
	 */
	@Override
	public ItemDTO getItem(String item) {
		try {
			return items.containsKey(item.toUpperCase()) ? items.get(item.toUpperCase()).clone() : null;
		} catch (CloneNotSupportedException e) {
			throw new ItemException("Unknown exception in cloning : ", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#containsItem(java.lang.String)
	 */
	@Override
	public boolean containsItem(String itemName) {
		return items.containsKey(itemName.toUpperCase());
	}

	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#updateItemValues(java.util.Map)
	 */
	@Override
	public void updateItemValues(Map<String, BigDecimal> itemVals) {
		for (Map.Entry<String, BigDecimal> entry : itemVals.entrySet()) {
			if (containsItem(entry.getKey())) {
				updateItemValue(entry.getKey(), entry.getValue());
			} else {
				throw new ItemException("Item " + entry.getKey() + " not found ");
			}
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#updateItemValue(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public void updateItemValue(String itemName, BigDecimal unitPric) {
		items.put(itemName.toUpperCase(), new ItemDTO(itemName.toUpperCase(), unitPric));
	}
	
	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#updateItemValue(java.lang.String, tw.gltc.shpng.dto.ItemDTO)
	 */
	@Override
	public void updateItemValue(ItemDTO item) {
		updateItemValue(item.getItemName(), item.getItemUnitPrice());
	}
	
	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#getItemValue(java.lang.String)
	 */
	@Override
	public BigDecimal getItemValue(String item) {
		BigDecimal itemValue = items.get(item) != null ? items.get(item).getItemUnitPrice() : new BigDecimal(0);
		return itemValue;
	}	
	
	/*
	 * (non-Javadoc)
	 * @see tw.gltc.shpng.ref.ItemRefIfc#getItemNames()
	 */
	@Override
	public Collection<String> getItemNames() {
		return items.keySet();
	}

	public static void main(String [] args) {
		GalacticItemRef itmRf = new GalacticItemRef();
		System.out.println(itmRf.getItemNames());
	}
}
