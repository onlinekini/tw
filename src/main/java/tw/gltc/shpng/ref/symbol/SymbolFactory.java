package tw.gltc.shpng.ref.symbol;

import tw.gltc.shpng.exception.ConversionException;

public class SymbolFactory {
	
	public static final String GALACTIC_SYMBOL_TYPE = "GALACTIC_SYMBOL";
		
	public static SymbolRefIfc getSymbolsFor(String type) {
		if (type.equalsIgnoreCase(GALACTIC_SYMBOL_TYPE)) {
			return GalacticSymbolRef.getInstance();
		} else {
			throw new ConversionException("Cannot find the Symbols to proceed ");
		}
		
	}
	
	
}
