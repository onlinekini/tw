package tw.mrchnt.guide.message;

import java.util.regex.Pattern;

public class SimpleMessageDecomposer  implements DecomposerIfc {

	String separators;
	
	
	public SimpleMessageDecomposer(String separator) {
		separators = separator;
	}
	
	@Override
	public String[] decomposeMessage(String message) {
		return Pattern.compile(separators).splitAsStream(message)
				.filter(o -> o.trim().length() > 0)
				.toArray(String[] :: new);
	}	
}
