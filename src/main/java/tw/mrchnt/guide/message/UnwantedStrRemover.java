package tw.mrchnt.guide.message;

import tw.mrchnt.guide.config.Reference;

public class UnwantedStrRemover  implements PrepperIfc {

	@Override
	public String prepMessage(String input) {
		String retVal = input;
		if (Reference.getReference().getProperty("symbol.ignore") != null) {
			for (String str : Reference.getReference().getProperty("symbol.ignore").split(",")) {
				retVal = retVal.replace(str, "");
			}
		}
		return retVal;
	}

	
}
