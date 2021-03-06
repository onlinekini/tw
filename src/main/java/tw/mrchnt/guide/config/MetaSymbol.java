package tw.mrchnt.guide.config;

/**
 * This class represents Glob, phish, teg... etc
 *
 */
public class MetaSymbol implements Cloneable {

	private final String symbolName;
	private final String romanNumEquivalent;
	private final int numericEquivalent;
	private final boolean canNegate;
	private final boolean canRepeat;
	
	public MetaSymbol(String symName, String romanEquivalent) {
		symbolName = symName;
		romanNumEquivalent = romanEquivalent;
		
		numericEquivalent = Integer.parseInt(Config.getReference().getProperty("symbol." + romanEquivalent.toUpperCase()));
		canNegate = !Config.getReference().getProperty("symbol.subs").contains(romanNumEquivalent);
		canRepeat = !Config.getReference().getProperty("symbol.reps").contains(romanNumEquivalent);
	}

	public String getSymbolName() {
		return symbolName;
	}

	public String getRomanNumEquivalent() {
		return romanNumEquivalent;
	}

	public int getNumericEquivalent() {
		return numericEquivalent;
	}

	public boolean canNegate() {
		return canNegate;
	}

	public boolean isRepeatable() {
		return canRepeat;
	}

	public boolean equals(MetaSymbol obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		return (obj.symbolName.equals(this.symbolName));
	}
	
	public boolean lessThan(MetaSymbol obj) {
		if (obj == null)
			return false;
		
		return obj.numericEquivalent > this.numericEquivalent;
	}

	@Override
	public int hashCode() {
		return this.numericEquivalent + symbolName.hashCode();
	}
	
	
	public String toString() {
		return symbolName;
	}
}
