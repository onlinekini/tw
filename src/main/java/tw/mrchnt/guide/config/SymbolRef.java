package tw.mrchnt.guide.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import tw.mrchnt.guide.symbol.MetaSymbol;

public class SymbolRef {

	private Map<String, MetaSymbol> symbolCataglogue;
	private static SymbolRef symbolRef;

	public static SymbolRef getSymbolRef() {
		if (symbolRef != null) {
			return symbolRef;
		} else {
			symbolRef = new SymbolRef();
			return symbolRef;
		}
	}

	public static SymbolRef createSymbolRef(List<MetaSymbol> metaSymbols) {
		if (symbolRef == null) {
			symbolRef = new SymbolRef();
		}
		symbolRef.addSymbols(metaSymbols);
		return symbolRef;
	}

	private SymbolRef() {
		symbolCataglogue = new HashMap<>();
	}

	public void addSymbols(List<MetaSymbol> itemRefs) {
		symbolCataglogue.putAll(itemRefs.stream().collect(Collectors.toMap(MetaSymbol::getSymbolName, Function.identity())));
	}

	public void addItem(MetaSymbol symbol) {
		symbolCataglogue.put(symbol.getSymbolName(), symbol);
	}

	public boolean isSymbol(String inputStr) {
		return symbolCataglogue.containsKey(inputStr);
	}
}
