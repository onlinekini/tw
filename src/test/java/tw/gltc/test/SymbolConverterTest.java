package tw.gltc.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.gltc.shpng.exception.ComputeException;
import tw.gltc.shpng.ref.symbol.SymbolFactory;
import tw.gltc.shpng.ref.symbol.SymbolRefIfc;
import tw.gltc.shpng.symbol.RomanToNumConverter;

public class SymbolConverterTest {

	static GalaticToNumric converter;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		SymbolRefIfc symRef = SymbolFactory.getSymbolsFor(SymbolFactory.GALACTIC_SYMBOL_TYPE);
		symRef.manualInit("glob", "I");
		symRef.manualInit("prok", "V");
		symRef.manualInit("pish", "X");
		symRef.manualInit("tegj", "L");
		symRef.manualInit("cfug", "C");
		symRef.manualInit("dfle", "D");
		symRef.manualInit("mgng", "M");
		converter = new GalaticToNumric(symRef);
	}

	@Test
	public void testSymbolConverter() {
		assertEquals(42, converter.convertToNumber("pish tegj glob glob "));
		assertEquals(4, converter.convertToNumber("glob prok "));
		assertEquals(1903, converter.convertToNumber("mgng cfug mgng glob glob glob"));
		
		assertEquals(39, converter.convertToNumber(" pish pish pish glob pish ")); // try with speaces before  & after
		assertEquals(1944, converter.convertToNumber(" mgng cfug mgng pish tegj glob prok")); // space before
		
		assertEquals(1944, converter.convertToNumber(new String[] {" mgng" , "cfug", "mgng",  "pish",  "tegj",  "glob", "prok"})); // space before
		assertEquals(1903, converter.convertToNumber("mgng    cfug mgng     glob glob glob")); // random spaces between symbols
	}

	@Test
	public void testSymbolConverterExcpn () { // Exception check
		exception.expect(ComputeException.class);
		exception.expectMessage("I have no idea what you are talking about");
		converter.convertToNumber("pish something "); // Get exception
	}



}
