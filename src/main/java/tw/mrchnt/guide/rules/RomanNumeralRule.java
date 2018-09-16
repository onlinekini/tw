package tw.mrchnt.guide.rules;

import java.util.Collection;

import tw.mrchnt.guide.config.ComputeException;
import tw.mrchnt.guide.config.MetaSymbol;

public class RomanNumeralRule implements NumeralRule {
	
	@Override
	public Double applyRule(Collection<MetaSymbol> metas) {
		if (!canApplyRule(metas)) {
			throw new ComputeException("Error Handling Metasymbols: " + metas + ", Cannot apply: " + RomanNumeralRule.class.getName());
		}
		MetaSymbol previous = null;
		Double totalValue = 0.0;
		for (MetaSymbol currMetaSymbol : metas) {
			if (currMetaSymbol.lessThan(previous)) {
				totalValue -= Double.valueOf(currMetaSymbol.getNumericEquivalent());
			} else {
				totalValue += Double.valueOf(currMetaSymbol.getNumericEquivalent());
			}
			previous = currMetaSymbol;
		}
		
		return totalValue;
	}

	@Override
	public boolean canApplyRule(Collection<MetaSymbol> metas) {
		return checkForRepeats(metas) && checkForNegation(metas);
	}
	
	private boolean checkForRepeats(Collection<MetaSymbol> metas) {
		int countOfRepeats = 0;
		MetaSymbol previous = null;
		for (MetaSymbol sym : metas) {
			if (sym.equals(previous)) {
				if (!sym.isRepeatable() || ++countOfRepeats > 2) {
					return false;
				}
			} else {
				countOfRepeats = 0;
				previous = sym;
			}
		}
		return true;
	}
	
	private boolean checkForNegation(Collection<MetaSymbol> metas) {
		MetaSymbol previous = null;
		for (MetaSymbol currMeta : metas) {
			if (currMeta.lessThan(previous) && !currMeta.canNegate()) {
				return false;
			}
		}
		return true;
	}
}
