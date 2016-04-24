package com.tw.galaxyguide.abhajoshi.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * CompoundToken is comprised of multiple tokens from say a compound statement or query.
 * like glob glob Silver is 34 Credits
 * 
 * Name = Silver
 * tokens = two simple tokens
 * credits = 34
 * 
 * @author abha
 *
 */
public class CompoundToken implements IToken{

	private List<SimpleToken> tokens;
	private String name;
	private Integer credits;
	
	public CompoundToken(String name, Integer credits){
		this.name = name;
		this.credits = credits;
		tokens = new ArrayList<>();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	public List<SimpleToken> getTokens() {
		return tokens;
	}

	public Integer getCredits() {
		return credits;
	}
	
	public void addToken(SimpleToken token){
		tokens.add(token);
	}
}

