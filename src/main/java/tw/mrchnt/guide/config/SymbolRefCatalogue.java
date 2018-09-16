package tw.mrchnt.guide.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SymbolRefCatalogue {

	private Map<String, MetaSymbol> symbolMap;
	private static SymbolRefCatalogue symbolCatalogue;

	public static SymbolRefCatalogue getSymbolCatalogue() {
		if (symbolCatalogue == null) {
			symbolCatalogue = new SymbolRefCatalogue();
		} 
		return symbolCatalogue;
	}
	
	
	private SymbolRefCatalogue() {
		symbolMap = new HashMap<>();
	}

	public void addSymbols(List<MetaSymbol> itemRefs) {
		symbolMap.putAll(itemRefs.stream().collect(Collectors.toMap(MetaSymbol::getSymbolName, Function.identity())));
	}

	public void addSymbol(MetaSymbol symbol) {
		symbolMap.put(symbol.getSymbolName(), symbol);
	}

	public boolean isSymbolPresent(String inputStr) {
		return symbolMap.containsKey(inputStr);
	}
	
	public MetaSymbol getMetaSymbol(String symbolName) {
		return symbolMap.get(symbolName);
	}
}
