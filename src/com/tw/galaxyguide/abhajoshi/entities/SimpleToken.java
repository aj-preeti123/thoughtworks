package com.tw.galaxyguide.abhajoshi.entities;

import com.tw.galaxyguide.abhajoshi.constants.Symbol;

/**
 * SimpleToken is composed of the tokens of say a Simple statement like glob is I
 * Name = glob
 * Value = I
 * 
 * @author abha
 *
 */
public class SimpleToken implements IToken, Comparable<SimpleToken>{

	private String name;
	private Symbol value;
		
	public SimpleToken(String name, String symbol) {
		this.name = name;
		try {
			this.value = Symbol.valueOf(symbol);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}		
	}
	
	public SimpleToken(String name, Symbol value) {
		this.name = name;
		this.value = value;			
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public Symbol getValue() {
		return value;
	}

	@Override
	public int compareTo(SimpleToken o) {
		return this.value.compareTo(o.getValue());
	}

	
}
