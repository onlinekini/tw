package tw.gltc.test;

import org.junit.BeforeClass;

import tw.gltc.shpng.config.ItemSource;
import tw.gltc.shpng.config.NumericToMathSource;
import tw.gltc.shpng.exception.ConversionException;
import tw.gltc.shpng.exception.ItemException;

public class GalacticShoppingTest {

	@BeforeClass
	public void initAll() {
		try {
			ItemSource.init();
			NumericToMathSource.init();
			
		} catch (ItemException e) {
			e.printStackTrace();
		} catch (ConversionException e) {
			e.printStackTrace();
		}
	}

}
