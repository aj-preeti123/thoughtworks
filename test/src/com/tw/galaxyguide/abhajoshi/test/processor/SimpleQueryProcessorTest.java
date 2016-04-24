package com.tw.galaxyguide.abhajoshi.test.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.SimpleQueryProcessor;
import com.tw.galaxyguide.abhajoshi.processor.SimpleStatementProcessor;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;

public class SimpleQueryProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEvaluate() {
		String s = "how much is glob glob ?";
		try {
			Context context = new Context();
			context.addToContext(new SimpleToken("glob", "I"), 1);
			Integer result = new SimpleQueryProcessor().evaluate(s, context);
			assertEquals(result.intValue(), 2);			
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException.class)
	public void testParseException() throws GalacticParseException, GalacticInsufficientInformationException {
		String s = "glob is nothing I";
		new SimpleStatementProcessor().evaluate(s, new Context());			
	}
}
