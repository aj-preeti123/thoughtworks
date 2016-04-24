package com.tw.galaxyguide.abhajoshi.test.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.tw.galaxyguide.abhajoshi.constants.Symbol;
import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.entities.IToken;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.SimpleStatementProcessor;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;

public class SimpleStatementProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEvaluate() {
		String s = "glob is I";
		try {
			int result = new SimpleStatementProcessor().evaluate(s, new Context());
			assertEquals(result, 1);			
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
