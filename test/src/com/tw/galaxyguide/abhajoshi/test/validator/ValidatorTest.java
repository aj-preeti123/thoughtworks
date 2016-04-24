package com.tw.galaxyguide.abhajoshi.test.validator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tw.galaxyguide.abhajoshi.entities.SimpleToken;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;
import com.tw.galaxyguide.abhajoshi.validation.Validator;

public class ValidatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValid() {
		try {			
			SimpleToken t1 = new SimpleToken("glob", "I");
			SimpleToken t2 = new SimpleToken("xlob", "X");
			List<SimpleToken> list = new ArrayList<>();
			list.add(t1);
			list.add(t2);
			list.add(t1);
			list.add(t1);
			list.add(t2);
			Validator.validate(list);
			assertTrue(true);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException.class)
	public void testRepeatsForI() throws GalacticValidationException {
		SimpleToken t1 = new SimpleToken("glob", "I");
		SimpleToken t2 = new SimpleToken("xlob", "X");
		List<SimpleToken> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t1);
		list.add(t1);
		list.add(t1);
		list.add(t2);
		Validator.validate(list);	
	}
	
	@Test(expected=com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException.class)
	public void testAllowableForD() throws GalacticValidationException {
		SimpleToken t1 = new SimpleToken("glob", "I");
		SimpleToken t2 = new SimpleToken("Dlob", "D");
		List<SimpleToken> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		list.add(t1);
		list.add(t2);
		Validator.validate(list);	
	}
	
}
