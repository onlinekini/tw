package tw.mrchnt.guide.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRefCatalogue {

	private Map<String, MetaItem> itemsMap;
	private static ItemRefCatalogue itemCatalogue;
	private List<String> itemNames;

	private ItemRefCatalogue() {
		itemsMap = new HashMap<>();
		itemNames = Arrays.asList(ItemConfig.getReference().getProperty("item.names").toLowerCase().split(","));
		itemsMap.put("", new MetaItem("", 1.0)); // HACKED null item
	}

	public static ItemRefCatalogue getItemRefCatalogue() {
		if (itemCatalogue == null) {
			itemCatalogue = new ItemRefCatalogue();
		}
		return itemCatalogue;
	}
	
	public void addItem(MetaItem itemRef) {
		itemsMap.put(itemRef.getItemName().toLowerCase(), itemRef);
	}

	public boolean isItem(String inputStr) {
		return itemNames.contains(inputStr.toLowerCase());// itemsMap.containsKey(inputStr.toLowerCase());
	}

	public MetaItem getItem(String inputStr) {
		return itemsMap.get(inputStr.toLowerCase());
	}
}
