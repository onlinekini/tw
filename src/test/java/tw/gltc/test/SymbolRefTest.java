package tw.gltc.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.ref.symbol.SymbolFactory;
import tw.gltc.shpng.ref.symbol.SymbolRefIfc;

public class SymbolRefTest {

	static SymbolRefIfc symRef;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		symRef = SymbolFactory.getSymbolsFor(SymbolFactory.GALACTIC_SYMBOL_TYPE);
	}

	@Test
	public void basicTest() { // Without Item
		symRef.manualInit("bing", "V");
		assertEquals(new Integer(5), symRef.getNumericEquivalent("bing"));
		
		symRef.manualInit("bing", "I");
		assertEquals(new Integer(1), symRef.getNumericEquivalent("bing"));
		
		symRef.manualInit("bing", "X");
		assertEquals(new Integer(10), symRef.getNumericEquivalent("bing"));
		
		symRef.manualInit("bing", "L");
		assertEquals(new Integer(50), symRef.getNumericEquivalent("bing"));
		
		symRef.manualInit("bing", "C");
		assertEquals(new Integer(100), symRef.getNumericEquivalent("bing"));
		
		symRef.manualInit("bing", "D");
		assertEquals(new Integer(500), symRef.getNumericEquivalent("bing"));
		
		symRef.manualInit("bing", "M");
		assertEquals(new Integer(1000), symRef.getNumericEquivalent("bing"));
	}
	
	@Test
	public void basicTestWithExcpn() { // Without Item
		exception.expect(ConversionException.class);
		exception.expectMessage("I have no idea what you are talking about");
		assertEquals(new Integer(5), symRef.getNumericEquivalent("xing"));
	}
	
	
	
}
