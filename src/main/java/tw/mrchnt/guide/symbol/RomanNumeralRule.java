package tw.mrchnt.guide.symbol;

public class RomanNumeralRule implements NumeralRuleIfc {
	
	private Symbols symbols;
	
	public RomanNumeralRule(Symbols symbols) {
		this.symbols = symbols;
	}
	
	@Override
	public void applyRule() {
		MetaSymbol previous = null;
		double totalValue = 0;
		for (MetaSymbol currMetaSymbol : symbols.getMetaSymbols()) {
			if (currMetaSymbol.lessThan(previous)) {
				totalValue -= currMetaSymbol.getNumericEquivalent();
			} else {
				totalValue += currMetaSymbol.getNumericEquivalent();
			}
		}
		symbols.setTotalValue(totalValue);
	}

	@Override
	public boolean canApplyRule() {
		return checkForRepeats(symbols) && checkForNegation(symbols);
	}
	
	private boolean checkForRepeats(Symbols symbols) {
		int countOfRepeats = 0;
		MetaSymbol previous = null;
		for (MetaSymbol sym : symbols.getMetaSymbols()) {
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
	
	private boolean checkForNegation(Symbols symbols) {
		MetaSymbol previous = null;
		for (MetaSymbol currMeta : symbols.getMetaSymbols()) {
			if (currMeta.lessThan(previous) && !currMeta.canNegate()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Symbols getSymbols() {
		return symbols;
	}


}
