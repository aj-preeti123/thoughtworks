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

public class CreditsStatementProcessor implements IProcessor{

	//glob glob Silver is 34 Credits
	static String regexCreditStatement = "((?:[a-z]+ )+)([A-Z]\\w+) is (\\d+) ([A-Z]\\w+)$";
	
	/**
	 * 
	 * @param expression
	 * @param context
	 * @return
	 * @throws GalacticParseException
	 * @throws GalacticInsufficientInformationException
	 * @throws GalacticValidationException
	 */
	private CompoundToken parse(String expression, Context context) throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException{
		Pattern pattern = Pattern.compile(regexCreditStatement);
        Matcher matcher = pattern.matcher(expression);
        if(!matcher.matches())
        	throw new GalacticParseException(expression + " cannot be parsed into SimpleToken. Check expression format for statement credits");
        
        String credits = matcher.group(3).trim();
        
        String[] tokens = matcher.group(1).split(" ");
        String metal = matcher.group(2).trim();
        
        CompoundToken compoundToken = new CompoundToken(metal, Integer.parseInt(credits));
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
		Integer number = new SimpleQueryProcessor().evaluate(token.getTokens(), context);
        int credit = token.getCredits();        
        
        context.addToContext(token, credit/number);
        return 1;
	}

}
