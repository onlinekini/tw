package tw.gltc.shpng;

import tw.gltc.shpng.config.NumericToMathSource;
import tw.gltc.shpng.exception.ConversionException;

public class CreditConverter {

	/**
	 * This method takes in the Galactic numeral input and then returns the mathematical equivalent
	 * This method expects that the caller checks the sourceCreditValue to be non Null. 
	 * if it is null then the method will fail catastrophically. 
	 * 
	 * @param sourceCreditValue . Non null Numeral set. This this cas Galactic numerals
	 * @return mathematical (or decimal to be precise) equivalent of the galatic numerals
	 */
	public int getMathematicalCreditValue(String sourceCredit) throws ConversionException {
		String[] numerals = sourceCredit.split(" ");		
		return getMathematicalCreditValue(numerals);
	}
	
	//TODO add Java doc
	public int getMathematicalCreditValue (String[] numerals) throws ConversionException {
		// Now that I have a queue /array of numerals
		int finalMathematicalValue = 0;
		int repeatCount = 0;
		int prev = 0; // Previous numeral, Mathematical equivalent , when looping
		int curr = 0; // current numeral, Mathematical equivalent, when looping

		for (int  i = numerals.length - 1; i >= 0 ; i--) {
			curr = NumericToMathSource.getMathematicalEquivalent(numerals[i]); 
			if (prev == 0) {
				// Add the mathematical Equivalent value to the final value
				finalMathematicalValue = curr;
			} else {
				if (repeatCount < 3) { // 0, 1, 2
					if (prev > curr) {
						finalMathematicalValue += curr;
						repeatCount = 0;
					} else if (prev == curr) {
						finalMathematicalValue += curr;
						repeatCount ++;
					} else { // Now if previous is less, check for subtraction rules - hence validate
						if (isValidSubtraction(prev, curr)) {
							repeatCount = 0;
							finalMathematicalValue -= curr;
						}
					}
				} else {
					throw new ConversionException("illegal usage of neumerals in " + numerals[i]);
				}
			}
		}
		return finalMathematicalValue;	
	}
	
	/*
	 * Its assumed that the previous value is greater than current value
	 */
	private boolean isValidSubtraction(int prev, int curr) {
		return !(prev > curr * 10  || (curr +"").startsWith("5"));
	}
}
