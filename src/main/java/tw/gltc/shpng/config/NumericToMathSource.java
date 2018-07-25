package tw.gltc.shpng.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
public class NumericToMathSource {

	private static Map<String, Integer> conversionValues;
	private static boolean isInError;
	
	
	public static void init() throws ConversionException {
		isInError = false;
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(new File("numericSrc.properties")));
			Set<String> propsKeys = props.stringPropertyNames();
			
			// Load the keys into the conversionValue Map
			conversionValues = new HashMap<>();
			for (String key : propsKeys) {
				conversionValues.put(key, Integer.parseInt(props.getProperty(key)));
			}
			System.out.println("Init of numeric infomation complete");
		} catch (FileNotFoundException e) {
			isInError = true;
			System.out.println(" File Not found : I cannot identify the source, hence cannot convert to human decimal numbers");
			throw new ConversionException("File not found, cannot start without that" , e);
		} catch (IOException e) {
			isInError = true;
			System.out.println(" Other IO Exception: I cannot identify the source, hence cannot convert to human decimal numbers");
			throw new ConversionException("Error reading source file, cannot start without that" , e);
		} catch (NumberFormatException nfe) { // Catching a runtime exception, but this is during start up, hence I believe this is better
			isInError = true;
			throw new ConversionException("the value of the properties should be integer numeric, cannot parse #"); //TODO get the exact key where this occurred for better User experience.
		}
	}
	
	
	public static void manualInit(Map<String, Integer> manualInput) throws ConversionException {
		conversionValues = manualInput;
	}
	
	
	public static Integer getMathematicalEquivalent(String galaticCurrencyName) throws ConversionException {
		if (!isInError && conversionValues == null) {
			init(); // Initialize the values
		} else {
			System.out.println(" cannot initialize, There was an error during setip, so please fix that first");
			throw new ConversionException("Error during setup, cannot proceed");
		}
		
		Integer val = conversionValues.get(galaticCurrencyName);
		if (val == null) {
			throw new ConversionException("I have no idea what you are talking about ");
		}
		
		return new Integer(val);
	}
	
	
	public static void main(String [] args) {
		try {
			NumericToMathSource.init();
			System.out.println(conversionValues);
		} catch (ConversionException e) {
			e.printStackTrace();
		}
		
	}
	
}
