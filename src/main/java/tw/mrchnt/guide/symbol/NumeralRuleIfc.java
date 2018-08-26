package tw.mrchnt.guide.symbol;

public interface NumeralRuleIfc {

	Symbols getSymbols();
	
	boolean canApplyRule();

	void applyRule();
	
}