package tw.gltc.shpng.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import tw.gltc.shpng.dto.ItemDTO;
import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.exception.ItemException;
import tw.gltc.shpng.ref.ItemRef;
import tw.gltc.shpng.ref.SymbolRef;

/**
 * This class is a helper to test the input from the Input.test file 
 * All the test cases which were provided in the requirement have been saved there.
 * 
 * This simply parses the input file and processes the line to idenfy if it is ... 
 * <ul>
 *   <li>symbol reference - source</li>
 *   <li>item reference - geting unit price of an item</li>
 *   <li>test conditions - Whatever the merchant wants to convert</li>
 *	</ul>
 *
 *	This class does not have a test case.
 * 
 * @author vkini
 *
 */
public class InteragalticMerchantSvc {

	private ItemValueComputer itemValueComputer;
	private ItemRef itmRef;
	private SymbolRef symblRef;
	private SymbolToNumberConverter symbolToNumConverter;

	public InteragalticMerchantSvc() {
		File inputFile = new File("Input.test");
		itmRef = ItemRef.getInstance();
		symblRef = SymbolRef.getInstance();
		symbolToNumConverter = new SymbolToNumberConverter(symblRef);
		itemValueComputer = new ItemValueComputer(symbolToNumConverter, itmRef);
		ItemDTO itmDTO = null;
		RequestDTO request = null;
		BufferedReader rdr = null;
		boolean isItemsetup = false;
		boolean isQuestion = false;
		
		String input = null;
		
		try {
			rdr = new BufferedReader(new FileReader(inputFile));
			while ((input = rdr.readLine()) != null) {
				if (input.startsWith("#")) {
					continue;
				}	
				isItemsetup = input.trim().toLowerCase().endsWith("credits");
				isQuestion = input.endsWith("?");

				if (input.trim().length() > 0) { // Empty line check
					if (isQuestion) { //Ending with a "?" so a question
						//System.out.println(" Computed Q String = " + input.substring(input.indexOf(" is ") + 4, input.indexOf("?")));
						request = itemValueComputer.getTotalValue(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")));
						if (request != null && request.hasItem() ) {
							System.out.println(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")) + " is " + request.getTotalPrice() + " Credits");
						} else if (request != null) {
							System.out.println(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")) + " is " + request.getTotalPrice());
						}
					} else if (isItemsetup) { // if it ends with credits and not ? it is item setup
						//System.out.println(" Computed Item String = " + input.substring(0, input.indexOf(" is ")).trim());
						//System.out.println(" Computed value = " + input.substring(input.indexOf("is")+2, input.indexOf("Credit")).trim());
						itmDTO = itemValueComputer.calculateItemValue(input.substring(0, input.indexOf(" is ")).trim(), new BigDecimal(input.substring(input.indexOf(" is ") + 4, input.indexOf("Credit")).trim()));
						itmRef.updateItemValue(itmDTO.getItemName(), itmDTO);
						//System.out.println();
					} else { //  // Neither a question or an item info, surely setup info
						//System.out.println(" Processing symbol String = " + Arrays.toString(input.trim().split("\\s*,\\s*")));
						String[] values = input.trim().split("\\s* \\s*"); // trimmed values
						symblRef.manualInit(values[0], values[2]);
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ConversionException e) {
			System.out.println(e.getMessage());
		} catch (ItemException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rdr != null) {
				try {
					rdr.close();
				} catch (IOException e) {
					// DO nothing for now
				}
			}
		}

	}
}
