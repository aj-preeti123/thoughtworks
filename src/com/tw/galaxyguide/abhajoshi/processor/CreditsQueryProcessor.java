package com.tw.galaxyguide.abhajoshi.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.galaxyguide.abhajoshi.constants.Symbol;
import com.tw.galaxyguide.abhajoshi.entities.CompoundToken;
import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

public class CreditsQueryProcessor implements IProcessor{
	
	//how many Credits is glob prok Silver ?
	private static String regexHowMany = "^how many Credits is (([a-z]+ )+)([A-Z]\\w+) \\?$";

	private CompoundToken parse(String expression, Context context) throws GalacticParseException, GalacticInsufficientInformationException {
		Pattern pattern = Pattern.compile(regexHowMany);
        Matcher matcher = pattern.matcher(expression);
        if(!matcher.matches())
        	throw new GalacticParseException(expression + " cannot be parsed. Check expression format for credits query");
        
        String[] tokens = matcher.group(1).split(" ");                
        String metal = matcher.group(3);
        
        CompoundToken compoundToken = new CompoundToken(metal, 0);
        for(int i=0; i < tokens.length; i++){
        	if (!context.exists(tokens[i]))
				throw new GalacticInsufficientInformationException("Cannot resolve value for " + tokens[i]);
        	
        	compoundToken.addToken(new SimpleToken(tokens[i], Symbol.getSymbol(context.getToken(tokens[i]))));
        }
        
        return compoundToken;
	}
	
	@Override
	public Integer evaluate(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		
		CompoundToken token = parse(expression, context);
		if (!context.exists(token.getName()))
			throw new GalacticInsufficientInformationException("Cannot resolve value for " + token.getName());
		
		int metal = context.getToken(token.getName());
		int sum =  new SimpleQueryProcessor().evaluate(token.getTokens(), context);
		return metal * sum;
	}

}
