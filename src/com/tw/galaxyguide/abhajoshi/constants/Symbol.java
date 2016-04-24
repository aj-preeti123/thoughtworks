package com.tw.galaxyguide.abhajoshi.constants;

/**
 * The Mapping of Roman Symbols to Decimal values
 * 
 * @author abha
 *
 */
public enum Symbol {

	I(1), V(5),X(10), L(50), C(100), D(500), M(1000);
	
	int decimalValue;
	
	/**
	 * 
	 * @param n
	 */
	Symbol(int n){
		this.decimalValue = n;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return this.name();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDecimalValue(){
		return this.decimalValue;
	}
	
	/**
	 * Return the Symbol represented by the decimal number
	 * or null if no such Symbol is found.
	 * 
	 * @param number
	 * @return
	 */
	public static Symbol getSymbol(Integer number){
		for(Symbol symbol: values()){
			if(symbol.decimalValue == number)
				return symbol;
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(Symbol.valueOf("10"));
	}
}
