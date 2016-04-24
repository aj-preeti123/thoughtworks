package com.tw.galaxyguide.abhajoshi.processor;

import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

/**
 * Interface for all Query Processors
 * 
 * @author abha
 *
 */
public interface IQueryProcessor extends IProcessor {

	/**
	 * Answers the expression query
	 * 
	 * @param expression
	 * @param context
	 * @return String the answer as string
	 * @throws GalacticParseException
	 *             : if parsing fails
	 * @throws GalacticInsufficientInformationException
	 *             : if there is not enough previous data to compute and answer
	 * @throws GalacticValidationException
	 *             : if application validations fail
	 */
	public String getAnswer(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException;
}
