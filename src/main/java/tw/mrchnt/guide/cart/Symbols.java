package tw.mrchnt.guide.cart;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import tw.mrchnt.guide.config.MetaSymbol;
import tw.mrchnt.guide.config.SymbolRefCatalogue;
import tw.mrchnt.guide.rules.NumeralRule;

/**
 * This class represents a group of Meta-symbols 
 * anf their numerical equivalent
 *
 */
public class Symbols implements Cloneable {

	private final Collection<MetaSymbol> metaSymbols;
	private Double totalValue;
		
	public Symbols(Collection<MetaSymbol> inMetaSymbols, NumeralRule numeralRule) {
		metaSymbols = inMetaSymbols;
		totalValue = numeralRule.applyRule(metaSymbols);
		
	}
	
	public Symbols(String[] inMetaSymbolStrs, NumeralRule numeralRule) {
		SymbolRefCatalogue symbolCatalogue = SymbolRefCatalogue.getSymbolCatalogue();
		
		metaSymbols = Arrays.stream(inMetaSymbolStrs)
				.map(o -> symbolCatalogue.getMetaSymbol(o))
				.collect(Collectors.toList());
		
		totalValue = numeralRule.applyRule(metaSymbols);
		
	}
	
	public Collection<MetaSymbol> getMetaSymbols() {
		return metaSymbols;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
	

}
