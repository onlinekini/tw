package tw.mrchnt.guide.rules;

import java.util.ArrayList;
import java.util.List;

import tw.mrchnt.guide.config.ItemRefCatalogue;
import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.message.MessageIfc;

public class DummyRule implements MessageRuleIfc {

	private static DummyRule rule;
	
	public static MessageRuleIfc getRule() {
		if(rule == null) {
			rule = new DummyRule();
		}
		return rule;
	}
	
	private DummyRule() {
		
	}
	
	
	@Override
	public void applyRule(MessageIfc message) {
		System.out.println("I have no idea what you are talking about");
	}

	@Override
	public boolean canApplyRule(MessageIfc message) {
		return true;
	}
}
