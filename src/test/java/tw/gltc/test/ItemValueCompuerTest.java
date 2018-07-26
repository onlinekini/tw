package tw.gltc.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.ref.ItemRef;
import tw.gltc.shpng.ref.SymbolRef;
import tw.gltc.shpng.service.ItemValueComputer;
import tw.gltc.shpng.service.SymbolToNumberConverter;

public class ItemValueCompuerTest {

	static ItemValueComputer itemValueComputer;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		SymbolRef symRef = SymbolRef.getInstance();
		ItemRef itmRef = ItemRef.getInstance();
		symRef.manualInit("glob", "I");
		symRef.manualInit("prok", "V");
		symRef.manualInit("pish", "X");
		symRef.manualInit("tegj", "L");
		symRef.manualInit("cfug", "C");
		symRef.manualInit("dfle", "D");
		symRef.manualInit("mgng", "M");
		
		itmRef.updateItemValue("Gold", new BigDecimal(10));
		itmRef.updateItemValue("Silver", new BigDecimal(100));

		SymbolToNumberConverter converter = new SymbolToNumberConverter(symRef);
		itemValueComputer = new ItemValueComputer(converter, itmRef);
	}

	@Test
	public void testGetTotalValueWithoutItem() { // Without Item
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setHasItem(false);
		reqDTO.setOriginalRequest("glob glob");
		reqDTO.setTotalPrice(new BigDecimal(2));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("glob glob"));
		
		reqDTO.setHasItem(false);
		reqDTO.setOriginalRequest("pish tegj glob  ");
		reqDTO.setTotalPrice(new BigDecimal(41));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("pish tegj glob  "));
		
		reqDTO.setHasItem(false);
		reqDTO.setOriginalRequest("pish pish pish glob ");
		reqDTO.setTotalPrice(new BigDecimal(31));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("pish pish pish glob "));
		
	}
	
	@Test
	public void testGetTotalValueWithItemNeg() { // With item and lots pof spaces before after and middle
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
		exception.expect(ConversionException.class);
		exception.expectMessage("I have no idea what you are talking about");
		RequestDTO reqDTO = new RequestDTO();
		reqDTO.setHasItem(true);
		reqDTO.setOriginalRequest("pish pish pish glob Gold Silver ");
		reqDTO.setTotalPrice(new BigDecimal(3100));
		assertEquals(reqDTO, itemValueComputer.getTotalValue("pish pish pish glob Gold Silver "));

	}

}
