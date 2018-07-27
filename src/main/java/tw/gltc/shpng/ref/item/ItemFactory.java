package tw.gltc.shpng.ref.item;

import tw.gltc.shpng.exception.ItemException;

public class ItemFactory {
	
	public static final String GALACTIC_ITEM_SRC_NAME = "GALACTIC_ITEMS";
		
	public static ItemRefIfc getItem(String type) {
		if (type.equalsIgnoreCase(GALACTIC_ITEM_SRC_NAME)) {
			return GalacticItemRef.getInstance();
		} else {
			throw new ItemException("Item type not found");
		}
		
	}
	
	
}
