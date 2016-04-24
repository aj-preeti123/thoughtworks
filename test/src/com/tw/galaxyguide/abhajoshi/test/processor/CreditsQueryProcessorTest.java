package com.tw.galaxyguide.abhajoshi.test.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.tw.galaxyguide.abhajoshi.entities.CompoundToken;
import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.CreditsQueryProcessor;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

public class CreditsQueryProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String expression = "how many Credits is glob glob Silver ?";
		Context context = new Context();
		context.addToContext(new SimpleToken("glob", "I"), 1);
		context.addToContext(new CompoundToken("Silver", 23), 17);
		try {
			int result = new CreditsQueryProcessor().evaluate(expression, context);
			assertEquals(result, 34);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException.class)
	public void testParseException() throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		String s = "how many Credits is not glob glob Silver";
		new CreditsQueryProcessor().evaluate(s, new Context());			
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException.class)
	public void testValidationException() throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		String s = "how many Credits is glob glob glob Silver ?";
		Context context = new Context();
		context.addToContext(new SimpleToken("glob", "I"), 1);
		context.addToContext(new CompoundToken("Silver", 23), 17);
		new CreditsQueryProcessor().evaluate(s, context);			
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException.class)
	public void testInsufficientException() throws GalacticParseException, GalacticInsufficientInformationException, GalacticValidationException {
		String s = "how many Credits is glob glob Silver ?";
		Context context = new Context();
		new CreditsQueryProcessor().evaluate(s, context);			
	}

}
