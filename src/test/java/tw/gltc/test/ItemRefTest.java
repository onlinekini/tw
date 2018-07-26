package tw.gltc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.gltc.shpng.ref.ItemRef;

public class ItemRefTest {

	static ItemRef itmRef;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		itmRef = ItemRef.getInstance();
	}

	@Test
	public void basicTest() { // Without Item
		assertEquals(Arrays.asList(new String[] {"GOLD", "SILVER", "IRON"}), itmRef.getItemNames());
		assertTrue(itmRef.containsItem("GOLD"));
		assertFalse(itmRef.containsItem("GOLFBALLS"));
		itmRef.updateItemValue("GOLFBALLS", new BigDecimal(20));
		assertTrue(itmRef.containsItem("GOLFBALLS"));
	}
	
	

	
}
