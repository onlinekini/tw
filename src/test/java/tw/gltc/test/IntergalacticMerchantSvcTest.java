package tw.gltc.test;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tw.gltc.shpng.service.InteragalticMerchantSvc;

public class IntergalacticMerchantSvcTest {

	static InteragalticMerchantSvc intergalacticMerchantSvc;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initAll() {
		intergalacticMerchantSvc = new InteragalticMerchantSvc();
	}

	@Test
	public void basicTest() { // Without Item
		intergalacticMerchantSvc.processInput();
	}
	
}
