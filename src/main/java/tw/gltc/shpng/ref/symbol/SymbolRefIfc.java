package tw.gltc.shpng.ref.symbol;

import java.util.Map;

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
public interface SymbolRefIfc {

	
	public void manualInit(Map<String, Integer> symbolValues);
	
	public void manualInit(String symbolName, String romanNumeral);
	
	public Integer getNumericEquivalent(String currencySymbol);
		
}
