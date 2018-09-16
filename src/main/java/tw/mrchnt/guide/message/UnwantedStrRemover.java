package tw.mrchnt.guide.message;

import tw.mrchnt.guide.config.Config;

public class UnwantedStrRemover  implements PrepperIfc {

	@Override
	public String prepMessage(String input) {
		String retVal = input;
		if (Config.getReference().getProperty("symbol.ignore.regex") != null) {
			input.replace(Config.getReference().getProperty("symbol.ignore.regex"), ""); //[^a-zA-Z0-9 ]
		}
		return retVal;
	}

	
}
