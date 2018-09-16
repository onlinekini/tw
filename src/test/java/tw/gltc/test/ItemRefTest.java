package tw.gltc.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaItem;

public class ItemRefTest {

	static ItemRefCatalogue itmRef;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		itmRef = ItemRefCatalogue.getItemRefCatalogue();
		itmRef.addItem(new MetaItem("GOLD", 5.0));
		itmRef.addItem(new MetaItem("SILVER", 5.0));
		itmRef.addItem(new MetaItem("IRON", 5.0));
	}

	@Test
	public void checkItemRefExists() {
		assertTrue(itmRef.isItem("GOLD"));
	}
	

	@Test
	public void addAndTest() {
		itmRef.addItem(new MetaItem("GOLFBALLS", 20.0));
		assertTrue(itmRef.isItem("GOLFBALLS"));
	}
	
	@Test
	public void checkItemRefNoExists() {
		assertFalse(itmRef.isItem("GOLFBALLS"));
	}
	
	

	
}
