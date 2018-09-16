package tw.mrchnt.guide.rules;

import java.util.Collection;

import tw.mrchnt.guide.config.MetaSymbol;

public interface NumeralRuleIfc {
	
	boolean canApplyRule(Collection<MetaSymbol> metas);

	Double applyRule(Collection<MetaSymbol> metas);
	
}