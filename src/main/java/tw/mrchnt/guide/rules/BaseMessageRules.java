package tw.mrchnt.guide.rules;

import tw.mrchnt.guide.message.MessageIfc;

public class BaseMessageRules {

	public static boolean endsWithQuestion(MessageIfc message) {
		return ("?").equals(message.getDecomposedMessage()[message.getDecomposedMessage().length - 1]);
	}
	
	public static boolean startsWithHowMuch(MessageIfc message) {
		return "how".equalsIgnoreCase(message.getDecomposedMessage()[0]) && "much".equalsIgnoreCase(message.getDecomposedMessage()[1]);
	}
	
	public static boolean startsWithHowMany(MessageIfc message) {
		return "how".equalsIgnoreCase(message.getDecomposedMessage()[0]) && "many".equalsIgnoreCase(message.getDecomposedMessage()[1]);
	}

	public static boolean thirdStringIS(MessageIfc message) {
		return "is".equalsIgnoreCase(message.getDecomposedMessage()[2]);
	}
	
	public static boolean fourthStringIs(MessageIfc message) {
		return "is".equalsIgnoreCase(message.getDecomposedMessage()[3]);
	}
	
}
