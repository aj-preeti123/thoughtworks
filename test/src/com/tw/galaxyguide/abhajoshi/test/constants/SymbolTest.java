package com.tw.galaxyguide.abhajoshi.test.constants;

import static org.junit.Assert.*;
import com.tw.galaxyguide.abhajoshi.constants.Symbol;

import org.junit.Before;
import org.junit.Test;

public class SymbolTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDecimalValue() {
		assertEquals(Symbol.I.getDecimalValue(), 1);
		assertEquals(Symbol.V.getDecimalValue(), 5);
		assertEquals(Symbol.M.getDecimalValue(), 1000);
	}
	
	@Test
	public void testGetSymbolFromDecimal() {
		assertEquals(Symbol.getSymbol(1), Symbol.I);
		assertEquals(Symbol.getSymbol(5), Symbol.V);
		assertEquals(Symbol.getSymbol(1000), Symbol.M);
		assertEquals(Symbol.getSymbol(5000), null);
	}

}
