package tw.gltc.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.mrchnt.guide.cart.Item;
import tw.mrchnt.guide.cart.Symbols;
import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaItem;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.rules.RomanNumeralRule;

public class ItemTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private static ItemRefCatalogue itemCatalogue;
	
	private static SymbolRefCatalogue symbolRefCatalogue;

	@BeforeClass
	public static void initAll() {
		
		// Symbol catalogue
		symbolRefCatalogue = SymbolRefCatalogue.getSymbolCatalogue();
		symbolRefCatalogue.addSymbol(new MetaSymbol("glob", "I"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("prok", "V"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("pish", "X"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("tegj", "L"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("cfug", "C"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("dfle", "D"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("mgng", "M"));
		
		// Item catalogue
		itemCatalogue = ItemRefCatalogue.getItemRefCatalogue();
		itemCatalogue.addItem(new MetaItem("GOLD", 14450.0));
		itemCatalogue.addItem(new MetaItem("SILVER", 17.0));
		itemCatalogue.addItem(new MetaItem("IRON", (391.0/2.0)));	
	}
	
	@Test
	public void testIron() {
		Item anItem = new Item(itemCatalogue.getItem("IRON"),  new Symbols(new String[] {"prok", "glob"}, new RomanNumeralRule()));
		assertEquals("782", anItem.getTotalItemValue());
	}
	
	@Test
	public void testGold() {
		Item anItem = new Item(itemCatalogue.getItem("GOLD"),  new Symbols(new String[] {"prok", "glob"}, new RomanNumeralRule()));
		assertEquals("57800", anItem.getTotalItemValue());
	}
	
	@Test
	public void testSilver() {
		Item anItem = new Item(itemCatalogue.getItem("SILVER"),  new Symbols(new String[] {"prok", "glob"}, new RomanNumeralRule()));
		assertEquals("68", anItem.getTotalItemValue());
	}

	
	
	/*@Test
	public void testGetTotalValueWithItemNeg() { // With item and lots pof spaces before after and middle
		MessageRuleIfc  itemDTO = new MessageRuleIfc("SILVER", new BigDecimal(17));
		
		assertEquals(itemDTO, itemValueComputer.calculateItemValue("glob glob Silver", new BigDecimal(34)));
	}
	
	@Test
	public void testCalcItemValue() { // With check if the unit price is being set
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setHasItem(true);
		reqDTO.setOriginalRequest("   glob glob      Gold   ");
		reqDTO.setTotalPrice(new BigDecimal(20));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("   glob glob      Gold   "));
	}
	
	@Test
	public void testGetTotalValueWithItem() { // Without Item
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setHasItem(true);
		reqDTO.setOriginalRequest("glob glob Gold");
		reqDTO.setTotalPrice(new BigDecimal(20));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("glob glob Gold"));
		
		reqDTO.setHasItem(true);
		reqDTO.setOriginalRequest("pish tegj glob Silver ");
		reqDTO.setTotalPrice(new BigDecimal(4100));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("pish tegj glob Silver "));
	}
	
	
	public void testWithException() {
		exception.expect(ComputeException.class);
		exception.expectMessage("I have no idea what you are talking about");
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setHasItem(true);
		reqDTO.setOriginalRequest("pish pish pish glob Gold Silver ");
		reqDTO.setTotalPrice(new BigDecimal(3100));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("pish pish pish glob Gold Silver "));

	}*/

}
