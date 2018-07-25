package tw.gltc.test;

import org.junit.BeforeClass;

import tw.gltc.shpng.ref.ItemRef;
import tw.gltc.shpng.ref.SymbolRef;

public class GalacticShoppingTest {

	SymbolRef symRef;
	ItemRef itmRef;
	
	@BeforeClass
	public void initAll() {
		symRef = SymbolRef.getInstance();
		itmRef = ItemRef.getInstance();
	}
	
	

}
