package tw.gltc.shpng.service;

import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.ref.SymbolRef;

public class SymbolToNumberConverter {
	
	private SymbolRef symbolRef;
	
	public SymbolToNumberConverter(SymbolRef symRef) {
		symbolRef = symRef;
	}

	/**
	 * This method takes in the Galactic numeral input and then returns the mathematical equivalent
	 * This method expects that the caller checks the sourceCreditValue to be non Null. 
	 * if it is null then the method will fail catastrophically. 
	 * 
	 * @param sourceCreditValue . Non null Numeral set. This this cas Galactic numerals
	 * @return mathematical (or decimal to be precise) equivalent of the galatic numerals
	 */
	public int convertToNumber(String symbolStr) throws ConversionException {
		//System.out.println("Converting : " + symbolStr);
		String[] symbols = symbolStr.trim().split(" ");		
		return convertToNumber(symbols);
	}
	

	/**
	 * Convert / compute to numeric value from symbol array
	 *     
	 * @param symbols
	 * @return int Value of the symbol
	 * @throws ConversionException
	 */
	public int convertToNumber (String[] symbols) throws ConversionException {
		// Now that I have a queue /array of numerals
		int finalNumericValue = 0;
		int repeatCount = 0;
		int prev = 0; // Previous numeral, Mathematical equivalent , when looping
		int curr = 0; // current numeral, Mathematical equivalent, when looping

		for (int  i = symbols.length - 1; i >= 0 ; i--) {
			curr = symbolRef.getNumericEquivalent(symbols[i]); 
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
		return finalNumericValue;	
	}
	
	/*
	 * Its assumed that the previous value is greater than current value
	 */
	private boolean isValidSubtraction(int prev, int curr) {
		return !(prev > curr * 10  || (curr +"").startsWith("5"));
	}
	
	
	public static void main(String[] args) {
		
		SymbolToNumberConverter converter = new SymbolToNumberConverter(SymbolRef.getInstance());
		converter.symbolRef.manualInit("glob", "I");
		converter.symbolRef.manualInit("prok", "V");
		converter.symbolRef.manualInit("pish", "X");
		converter.symbolRef.manualInit("tegj", "L");
		converter.symbolRef.manualInit("cfug", "C");
		converter.symbolRef.manualInit("dfle", "D");
		converter.symbolRef.manualInit("mgng", "M");
		
		try {
			System.out.println("pish tegj glob glob " + converter.convertToNumber("pish tegj glob glob "));
			System.out.println("glob prok " + converter.convertToNumber("glob prok "));
			System.out.println("pish pish pish glob pish " + converter.convertToNumber("pish pish pish glob pish"));
			System.out.println("MCMXLIV " + converter.convertToNumber(" mgng cfug mgng pish tegj glob prok"));
			System.out.println("MCMIII " + converter.convertToNumber("mgng cfug mgng glob glob glob"));
		} catch (ConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
