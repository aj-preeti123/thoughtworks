package com.tw.galaxyguide.abhajoshi.test.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.tw.galaxyguide.abhajoshi.constants.Symbol;
import com.tw.galaxyguide.abhajoshi.entities.CompoundToken;
import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;

public class CompoundTokenTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddToken() {
		CompoundToken compoundToken = new CompoundToken("Test1", 12);
		assertTrue(compoundToken.getTokens().isEmpty());
		SimpleToken simpleToken = new SimpleToken("Simple", "D");
		compoundToken.addToken(simpleToken);
		assertEquals(compoundToken.getTokens().size(), 1);
	}

}
