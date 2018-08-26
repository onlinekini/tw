package tw.mrchnt.guide.request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import tw.gltc.shpng.dto.Item;
import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ComputeException;
import tw.gltc.shpng.exception.ItemException;
import tw.gltc.shpng.ref.item.ItemFactory;
import tw.gltc.shpng.ref.item.ItemRefIfc;
import tw.gltc.shpng.ref.symbol.SymbolFactory;
import tw.gltc.shpng.ref.symbol.SymbolRefIfc;
import tw.gltc.shpng.symbol.RomanToNumConverter;

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
public class GalticMerchantSvc {

	private ItemValueComputer itemValueComputer;
	private ItemRefIfc itmRef;
	private SymbolRefIfc symblRef;
	private GalaticToNumric symbolToNumConverter;

	public static void main(String[] args) {
		GalticMerchantSvc merchantScv = new GalticMerchantSvc();
		merchantScv.processInput();
	}
	
	public GalticMerchantSvc() {
		itmRef = ItemFactory.getItem(ItemFactory.GALACTIC_ITEM_SRC_NAME);
		symblRef = SymbolFactory.getSymbolsFor(SymbolFactory.GALACTIC_SYMBOL_TYPE);
		symbolToNumConverter = new GalaticToNumric(symblRef);
		itemValueComputer = new ItemValueComputer(symbolToNumConverter, itmRef);
	}

	public void processInput() {

		File inputFile = new File("Input.test");
		
		MessageRuleIfc itmDTO = null;
		RequestDTO request = null;
		BufferedReader rdr = null;
		boolean isItemsetup = false;
		boolean isQuestion = false;
		boolean isConversion = false;

		String input = null;

		try {
			rdr = new BufferedReader(new FileReader(inputFile));
			while ((input = rdr.readLine()) != null) {
				if (input.startsWith("#")) {
					continue;
				}	
				isItemsetup = input.trim().toLowerCase().endsWith("credits");
				isQuestion = input.endsWith("?");
				isConversion = (input.endsWith("?") && (itemValueComputer.containsMoreThanOneItem(input) == 2));

				if (input.trim().length() > 0) { // Empty line check
					if (isConversion) {
						//System.out.println(isConversion);
						System.out.println(itemValueComputer.calculateItemValueFromAnother(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")), input.substring(input.indexOf(" many ") + 4, input.indexOf(" is "))));
					} else if (isQuestion) { //Ending with a "?" so a question
						request = itemValueComputer.getTotalValue(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")));
						
						
						if (request != null && request.hasItem() ) {
							System.out.println(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")) + " is " + request.getTotalPrice() + " Credits");
						} else if (request != null) {
							System.out.println(input.substring(input.indexOf(" is ") + 4, input.indexOf("?")) + " is " + request.getTotalPrice());
						}
					} else if (isItemsetup) { // if it ends with credits and not ? it is item setup
						itmDTO = itemValueComputer.calculateItemValue(input.substring(0, input.indexOf(" is ")).trim(), new BigDecimal(input.substring(input.indexOf(" is ") + 4, input.indexOf("Credit")).trim()));
						itmRef.updateItemValue(itmDTO);
					} else { //  // Neither a question or an item info, surely setup info
						String[] values = input.trim().split("\\s* \\s*"); // trimmed values
						symblRef.manualInit(values[0], values[2]);
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ComputeException e) {
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
