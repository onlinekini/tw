package tw.mrchnt.guide.rules;

import tw.mrchnt.guide.message.MessageIfc;

public interface MessageRuleIfc {
	
	void applyRule(MessageIfc message);
	
	boolean canApplyRule(MessageIfc message);
	
}