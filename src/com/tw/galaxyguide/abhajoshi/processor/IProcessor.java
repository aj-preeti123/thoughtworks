package com.tw.galaxyguide.abhajoshi.processor;

import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

/**
 * Interface for all Statement and Query Processors
 * 
 * @author abha
 *
 */
public interface IProcessor {

	/**
	 * Evaluates the expression. For statement processors, returns 1 For query
	 * procsessors, returns the computed result.
	 * 
	 * @param expression
	 * @param context
	 * @return Integer
	 * @throws GalacticParseException
	 *             : if parsing fails
	 * @throws GalacticInsufficientInformationException
	 *             : if there is not enough previous data to compute and answer
	 * @throws GalacticValidationException
	 *             : if application validations fail
	 */
	public Integer evaluate(String expression, Context context)
			throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException;

	/**
	 * Returns true, if the processor is capabale to parse this expression
	 * 
	 * @param expression
	 * @return
	 */
	public boolean matchesMyType(String expression);
}
