package tw.mrchnt.guide.symbol;

public class Symbols implements Cloneable {

	final MetaSymbol[] metaSymbols;
	private double totalValue;
		
	Symbols(MetaSymbol[] metas) {
		metaSymbols = metas;
	}
	
	public MetaSymbol[] getMetaSymbols() {
		return metaSymbols;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
	

}
