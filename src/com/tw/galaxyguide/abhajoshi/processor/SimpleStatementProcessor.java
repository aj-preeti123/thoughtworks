package com.tw.galaxyguide.abhajoshi.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.IToken;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;

/**
 * Processes simple statement like glob is I.
 * 
 * @author abha
 *
 */
public class SimpleStatementProcessor implements IProcessor {

	private static String regexSimple = "^([a-z]+) is ([I|V|X|L|C|D|M])$";

	/**
	 * Parse the expression
	 * 
	 * @param expression
	 * @param context
	 * @return
	 * @throws GalacticParseException
	 */
	private IToken parse(String expression, Context context) throws GalacticParseException {
		Pattern pattern = Pattern.compile(regexSimple);
		Matcher matcher = pattern.matcher(expression);
		if (!matcher.matches())
			throw new GalacticParseException(
					expression + " cannot be parsed into SimpleToken. Check expression format");

		SimpleToken term = new SimpleToken(matcher.group(1), matcher.group(2));

		return term;
	}

	/**
	 * Returns 1, if evaluation is successful else throws an exception
	 */
	@Override
	public Integer evaluate(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException {

		IToken token = parse(expression, context);
		SimpleToken sToken = (SimpleToken) token;
		context.addToContext(sToken, sToken.getValue().getDecimalValue());
		return 1;
	}

	/**
	 * Returns true if the expression matches the type of this processor
	 */
	@Override
	public boolean matchesMyType(String expression) {
		Pattern pattern = Pattern.compile(regexSimple);
		Matcher matcher = pattern.matcher(expression);
		if (!matcher.matches())
			return false;

		return true;
	}

}
