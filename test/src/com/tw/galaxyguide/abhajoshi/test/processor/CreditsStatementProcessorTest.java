package com.tw.galaxyguide.abhajoshi.test.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.CreditsStatementProcessor;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

public class CreditsStatementProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String expression = "glob glob Silver is 34 Credits";
		Context context = new Context();
		context.addToContext(new SimpleToken("glob", "I"), 1);
		try {
			int result = new CreditsStatementProcessor().evaluate(expression, context);
			assertEquals(result, 1);
			assertEquals(context.getToken("Silver").intValue(), 17);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException.class)
	public void testParseException() throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		String s = "glob glob Silver is 34 Credits and something more";
		new CreditsStatementProcessor().evaluate(s, new Context());			
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException.class)
	public void testValidationException() throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		String s = "glob glob glob Silver is 34 Credits";
		Context context = new Context();
		context.addToContext(new SimpleToken("glob", "I"), 1);
		new CreditsStatementProcessor().evaluate(s, context);			
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException.class)
	public void testInsufficientException() throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		String s = "glob glob Silver is 34 Credits";
		Context context = new Context();
		new CreditsStatementProcessor().evaluate(s, context);			
	}
}
