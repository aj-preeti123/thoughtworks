package com.tw.galaxyguide.abhajoshi.util;

import java.util.List;

import com.tw.galaxyguide.abhajoshi.constants.Symbol;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

/**
 * Performs validation routines
 * 
 * @author abha
 *
 */
public class Validator {

	/**
	 * The validation routine
	 * 
	 * @param tokens
	 * @throws GalacticValidationException
	 */
	public static void validate(List<SimpleToken> tokens) throws GalacticValidationException {
		valiateConsecutiveRepeats(tokens, Symbol.I);
		valiateConsecutiveRepeats(tokens, Symbol.X);
		valiateConsecutiveRepeats(tokens, Symbol.C);
		valiateConsecutiveRepeats(tokens, Symbol.M);

		valiateAllowableRepeats(tokens, Symbol.D);
		valiateAllowableRepeats(tokens, Symbol.L);
		valiateAllowableRepeats(tokens, Symbol.V);
	}

	/**
	 * Checks if a Symbol can be repeated more than 3 times in succession
	 * 
	 * @param tokens
	 * @param symbol
	 * @throws GalacticValidationException
	 */
	private static void valiateConsecutiveRepeats(List<SimpleToken> tokens, Symbol symbol)
			throws GalacticValidationException {
		for (int i = 0; i < tokens.size(); i++) {
			if ((i + 1) < tokens.size() && (i + 2) < tokens.size()) {
				if (tokens.get(i).getValue() == symbol && tokens.get(i + 1).getValue() == symbol
						&& tokens.get(i + 2).getValue() == symbol)
					throw new GalacticValidationException(symbol + "cannot occur more than 3 times");
			}
		}
	}

	/**
	 * Checks if a Symbol can be repeated at all.
	 * 
	 * @param tokens
	 * @param symbol
	 * @throws GalacticValidationException
	 */
	private static void valiateAllowableRepeats(List<SimpleToken> tokens, Symbol symbol)
			throws GalacticValidationException {
		int count = 0;
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).getValue() == symbol) {
				count++;
			}
			if (count > 1) {
				throw new GalacticValidationException(symbol + "can never be repeated");
			}
		}
		if (count > 1) {
			throw new GalacticValidationException(symbol + "can never be repeated");
		}
	}
}
