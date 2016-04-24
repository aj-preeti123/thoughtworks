package com.tw.galaxyguide.abhajoshi.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tw.galaxyguide.abhajoshi.constants.Symbol;
import com.tw.galaxyguide.abhajoshi.entities.CompoundToken;
import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;
import com.tw.galaxyguide.abhajoshi.util.Validator;

/**
 * Evulates and answers the query of type how much is pish tegj glob glob ?
 * 
 * @author abha
 *
 */
public class SimpleQueryProcessor implements IQueryProcessor {

	// how much is pish tegj glob glob ?
	private static String regexHowMuch = "^how much is (([a-z]+ )+)\\?$";

	/**
	 * Parses the expression
	 * 
	 * 
	 * @param expression
	 * @param context
	 * @return CompoundToken
	 * @throws GalacticParseException
	 * @throws GalacticInsufficientInformationException
	 * @throws GalacticValidationException
	 */
	private CompoundToken parse(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		Pattern pattern = Pattern.compile(regexHowMuch);
		Matcher matcher = pattern.matcher(expression);
		if (!matcher.matches())
			throw new GalacticParseException(
					expression + " cannot be parsed into SimpleToken. Check expression format");

		String[] tokens = matcher.group(1).split(" ");
		CompoundToken compoundToken = new CompoundToken("Temp", 0);

		List<SimpleToken> list = new ArrayList<>();
		for (String str : tokens) {
			if (!context.exists(str))
				throw new GalacticInsufficientInformationException("Cannot resolve value for " + str);
			SimpleToken token = new SimpleToken(str, Symbol.getSymbol(context.getDecimalValue(str)).name());
			list.add(token);
			compoundToken.addToken(token);
		}

		Validator.validate(list);

		return compoundToken;
	}

	/*
	 * 
	 */
	public int evaluate(List<SimpleToken> tokens, Context context) throws GalacticValidationException {
		if (tokens.size() == 1) {
			return context.getDecimalValue(tokens.get(0).getName());
		}

		Validator.validate(tokens);

		int sum = 0;
		for (int i = 0; i < tokens.size();) {
			if ((i + 1) < tokens.size() && (tokens.get(i).compareTo(tokens.get(i + 1)) == -1)) {
				sum = sum + (context.getDecimalValue(tokens.get(i + 1).getName())
						- context.getDecimalValue(tokens.get(i).getName()));
				i += 2;
			} else {
				sum = sum + tokens.get(i).getValue().getDecimalValue();
				i++;
			}
		}

		return sum;
	}

	/**
	 * Evaluates the expression and returns an Integer result
	 * 
	 * @throws GalacticValidationException
	 * 
	 */
	@Override
	public Integer evaluate(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {

		CompoundToken token = parse(expression, context);
		List<SimpleToken> tokens = token.getTokens();
		if (tokens == null || tokens.isEmpty())
			throw new GalacticInsufficientInformationException("Insufficient data to evaluate " + expression);

		return evaluate(tokens, context);
	}

	/**
	 * Answers the query
	 * 
	 */
	@Override
	public String getAnswer(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		CompoundToken token = parse(expression, context);
		List<SimpleToken> tokens = token.getTokens();
		if (tokens == null || tokens.isEmpty())
			throw new GalacticInsufficientInformationException("Insufficient data to evaluate " + expression);

		Integer result = evaluate(tokens, context);
		StringBuilder builder = new StringBuilder();
		for (SimpleToken sToken : tokens) {
			builder.append(sToken.getName() + " ");
		}
		builder.append("is ").append(result.toString());

		return builder.toString();
	}

	/**
	 * Returns true if expression matches the processor type
	 */
	@Override
	public boolean matchesMyType(String expression) {
		Pattern pattern = Pattern.compile(regexHowMuch);
		Matcher matcher = pattern.matcher(expression);
		if (!matcher.matches())
			return false;

		return true;
	}
}
