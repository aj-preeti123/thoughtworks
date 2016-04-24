package com.tw.galaxyguide.abhajoshi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tw.galaxyguide.abhajoshi.test.constants.SymbolTest;
import com.tw.galaxyguide.abhajoshi.test.entities.CompoundTokenTest;
import com.tw.galaxyguide.abhajoshi.test.processor.CreditsQueryProcessorTest;
import com.tw.galaxyguide.abhajoshi.test.processor.CreditsStatementProcessorTest;
import com.tw.galaxyguide.abhajoshi.test.processor.SimpleQueryProcessorTest;
import com.tw.galaxyguide.abhajoshi.test.processor.SimpleStatementProcessorTest;
import com.tw.galaxyguide.abhajoshi.test.validator.ValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ SymbolTest.class, CompoundTokenTest.class, CreditsStatementProcessorTest.class,
		CreditsQueryProcessorTest.class, SimpleStatementProcessorTest.class, SimpleQueryProcessorTest.class,
		ValidatorTest.class })
public class AllTests {

}
