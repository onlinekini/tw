package tw.gltc.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.mrchnt.guide.cart.Symbols;
import tw.mrchnt.guide.config.ComputeException;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.rules.RomanNumeralRule;

public class SymbolsTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		SymbolRefCatalogue symbolRefCatalogue = SymbolRefCatalogue.getSymbolCatalogue();
		symbolRefCatalogue.addSymbol(new MetaSymbol("glob", "I"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("prok", "V"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("pish", "X"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("tegj", "L"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("cfug", "C"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("dfle", "D"));
		symbolRefCatalogue.addSymbol(new MetaSymbol("mgng", "M"));
	}
	
	@Test
	public void testMCMIII() { // Without Item
		Symbols symbols = new Symbols(new String[] {"glob", "glob", "glob", "mgng", "cfug", "mgng"}, new RomanNumeralRule());
		assertEquals(Double.valueOf(1903), symbols.getTotalValue());
	}

	@Test
	public void testValueTwoSymbols() { // Without Item
		Symbols symbols = new Symbols(new String[] {"glob", "glob"}, new RomanNumeralRule());
		assertEquals(Double.valueOf(2), symbols.getTotalValue());
	}
	
	
	@Test
	public void testValueThreeSymbols() { // Without Item
		Symbols symbols = new Symbols(new String[] {"glob", "tegj", "pish"}, new RomanNumeralRule());
		assertEquals(Double.valueOf(41), symbols.getTotalValue());
	}
	
	@Test
	public void testValueFourSymbols() { // Without Item
		Symbols symbols = new Symbols(new String[] {"glob", "pish", "pish", "pish"}, new RomanNumeralRule());
		assertEquals(Double.valueOf(31), symbols.getTotalValue());
	}
	
	@Test
	public void testException() { // Without Item
		exception.expect(ComputeException.class);
		Symbols symbols = new Symbols(new String[] {"pish", "pish", "pish", "pish"}, new RomanNumeralRule());
		assertEquals(Double.valueOf(31), symbols.getTotalValue());
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
