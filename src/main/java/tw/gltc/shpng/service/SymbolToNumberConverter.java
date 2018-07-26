package tw.gltc.shpng.service;

import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.ref.SymbolRef;

/**
 * This class does the computation from symbols to methemacitcal value. Provided the requirments have valid symbols
 * 
 * Uses {@link SymbolToNumberConverter} to convert symbols to numeric values as constructor argument.
 * 
 * @author vkini
 *
 */
public class SymbolToNumberConverter {
	
	private SymbolRef symbolRef;
	
	public SymbolToNumberConverter(SymbolRef symRef) {
		symbolRef = symRef;
	}

	/**
	 * This method takes in the Galactic symbol input STRING and then returns the
	 * numeric equivalent This method expects that the caller null check for
	 * arguments. if argument is null then the method will fail catastrophically.
	 * 
	 * Throws a runtimeException {@link ConversionException} if the symbol has an
	 * illegal symbol. Illegal symbols are the ones which are not defined as a
	 * symbol
	 * 
	 * @param symbolStr
	 * @return the int(primitive) value .
	 */
	public int convertToNumber(String symbolStr) { 
		// Ideally return type should be Integer, but felt too tired to null check other things, henc eprimitive
		//System.out.println("Converting : " + symbolStr); // TODO Keeping the sysouts for testing. Ideally to be removed in prod.
		String[] symbols = symbolStr.trim().split(" ");		
		return convertToNumber(symbols);
	}
	

	/**
	 * Convert / compute to numeric value from symbol array
	 * This method is a "split values - array" version of the {@link SymbolToNumberConverter#convertToNumber(String)}} method
	 * 
	 * @param symbols An array of symbols. It may have spaces as array values which will be ignored
	 * @return
	 */
	public int convertToNumber (String[] symbols) {
		// Now that I have a queue /array of numerals
		int finalNumericValue = 0;
		int repeatCount = 0;
		int prev = 0; // Previous numeral, Mathematical equivalent , when looping
		int curr = 0; // current numeral, Mathematical equivalent, when looping

		for (int  i = symbols.length - 1; i >= 0 ; i--) {
			if (symbols[i].trim().length() > 0) {
				curr = symbolRef.getNumericEquivalent(symbols[i].trim()); 
				//System.out.println(curr + " .." + prev + " .. " + repeatCount);
				if (prev == 0) {
					// Add the mathematical Equivalent value to the final value
					finalNumericValue = curr;
				} else {
					if (repeatCount < 3) { // 0, 1, 2
						if (prev > curr) {
							if (isValidSubtraction(prev, curr)) {
								repeatCount = 0;
								finalNumericValue -= curr;
							}
						} else if (prev == curr) {
							finalNumericValue += curr;
							repeatCount ++;
						} else { // Now if previous is more, check for subtraction rules - hence validate
							finalNumericValue += curr;
							repeatCount = 0;
						}
					} else {
						throw new ConversionException("illegal usage of neumerals in " + symbols[i]);
					}
				}
				//System.out.println(finalNumericValue);
				prev = curr;
			}
		}
		return finalNumericValue;	
	}

	//Used to compute the subtraction rule
	private boolean isValidSubtraction(int prev, int curr) {
		// Its assumed that the previous value is lesser than current value when it comes here
		return !(prev > curr * 10  || (curr +"").startsWith("5"));
	}
	
	
	
	/**
	 * Main method : Standalone test for development purposes. 
	 * This method may safely ignored even for testing purposes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SymbolToNumberConverter converter = new SymbolToNumberConverter(SymbolRef.getInstance());
		converter.symbolRef.manualInit("I", "I");
		converter.symbolRef.manualInit("V", "V");
		converter.symbolRef.manualInit("X", "X");
		converter.symbolRef.manualInit("L", "L");
		converter.symbolRef.manualInit("C", "C");
		converter.symbolRef.manualInit("D", "D");
		converter.symbolRef.manualInit("M", "M");
		
		System.out.println("MCMXLIV " + converter.convertToNumber("M C M X L I V "));
		System.out.println("MCMIII " + converter.convertToNumber("M C M I I I"));
	}
}
