package tw.mrchnt.guide.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import tw.mrchnt.guide.item.MetaItem;

public class ItemRef {

	private Map<String, MetaItem> itemCataglogue;
	private static ItemRef itemProcessor;

	public static ItemRef getItemRef() {
		if (itemProcessor != null) {
			return itemProcessor;
		} else {
			itemProcessor = new ItemRef();
			return itemProcessor;
		}
	}

	public static ItemRef createItemProcessor(List<MetaItem> itemRefs) {
		if (itemProcessor == null) {
			itemProcessor = new ItemRef();
		}
		itemProcessor.addItems(itemRefs);
		return itemProcessor;
	}

	private ItemRef() {
		itemCataglogue = new HashMap<>();
	}

	public void addItems(List<MetaItem> itemRefs) {
		itemCataglogue.putAll(itemRefs.stream().collect(Collectors.toMap(MetaItem::getItemName, Function.identity())));
	}

	public void addItem(MetaItem itemRef) {
		itemCataglogue.put(itemRef.getItemName(), itemRef);
	}

	public boolean isItem(String inputStr) {
		return itemCataglogue.containsKey(inputStr);
	}

	public Optional<MetaItem> getItem(String inputStr) {
		return Optional.ofNullable(new MetaItem(itemCataglogue.get(inputStr).getItemName(),
				itemCataglogue.get(inputStr).getItemUnitPrice()));
	}
}
