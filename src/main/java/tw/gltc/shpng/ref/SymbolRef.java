package tw.gltc.shpng.ref;

import java.util.HashMap;
import java.util.Map;

import tw.gltc.shpng.exception.ConversionException;

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
public class SymbolRef {

	private Map<String, Integer> symbolConversionValues;
	private static SymbolRef symbolRef;
	private static Map<String, Integer> romanNumerals;
	

	private SymbolRef() {
		symbolConversionValues = new HashMap<>();
		romanNumerals = new HashMap<>();
		romanNumerals.put("I", 1);
		romanNumerals.put("V",  5);
		romanNumerals.put("X", 10);
		romanNumerals.put("L", 50);
		romanNumerals.put("C", 100);
		romanNumerals.put("D", 500);
		romanNumerals.put("M", 1000); // setup
	}
	
	public static SymbolRef getInstance() {
		symbolRef = new SymbolRef();
		return symbolRef;
	}
	
	public void manualInit(Map<String, Integer> symbolValues) throws ConversionException {
		symbolConversionValues = symbolValues;
	}
	
	public void manualInit(String symbolName, String romanNumeral) {
		symbolConversionValues.put(symbolName, romanNumerals.get(romanNumeral));
	}
	
	
	public Integer getNumericEquivalent(String currencySymbol) throws ConversionException {
		Integer val = symbolConversionValues.get(currencySymbol);
		if (val == null) {
			throw new ConversionException("I have no idea what you are talking about: " + currencySymbol);
		}
		
		return new Integer(val);
	}
		
}
