package tw.gltc.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.gltc.shpng.dto.Item;
import tw.gltc.shpng.dto.RequestDTO;
import tw.gltc.shpng.exception.ComputeException;
import tw.gltc.shpng.ref.item.ItemFactory;
import tw.gltc.shpng.ref.item.ItemRefIfc;
import tw.gltc.shpng.ref.symbol.SymbolFactory;
import tw.gltc.shpng.ref.symbol.SymbolRefIfc;
import tw.gltc.shpng.service.ItemValueComputer;
import tw.gltc.shpng.symbol.RomanToNumConverter;

public class ItemValueCompuerTest {

	static ItemValueComputer itemValueComputer;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		SymbolRefIfc symRef = SymbolFactory.getSymbolsFor(SymbolFactory.GALACTIC_SYMBOL_TYPE);
		ItemRefIfc itmRef = ItemFactory.getItem(ItemFactory.GALACTIC_ITEM_SRC_NAME);
		symRef.manualInit("glob", "I");
		symRef.manualInit("prok", "V");
		symRef.manualInit("pish", "X");
		symRef.manualInit("tegj", "L");
		symRef.manualInit("cfug", "C");
		symRef.manualInit("dfle", "D");
		symRef.manualInit("mgng", "M");
		
		itmRef.updateItemValue("Gold", new BigDecimal(10));
		itmRef.updateItemValue("Silver", new BigDecimal(100));

		GalaticToNumric converter = new GalaticToNumric(symRef);
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

	}

}
