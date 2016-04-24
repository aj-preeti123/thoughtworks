package com.tw.galaxyguide.abhajoshi.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.tw.galaxyguide.abhajoshi.entities.Context;
import com.tw.galaxyguide.abhajoshi.processor.CreditsQueryProcessor;
import com.tw.galaxyguide.abhajoshi.processor.CreditsStatementProcessor;
import com.tw.galaxyguide.abhajoshi.processor.IProcessor;
import com.tw.galaxyguide.abhajoshi.processor.IQueryProcessor;
import com.tw.galaxyguide.abhajoshi.processor.SimpleQueryProcessor;
import com.tw.galaxyguide.abhajoshi.processor.SimpleStatementProcessor;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticInsufficientInformationException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticParseException;
import com.tw.galaxyguide.abhajoshi.processor.exception.GalacticValidationException;

/**
 * The Application.
 * 
 * 1. Reads the input text file
 * 2. For each line of input, determines which IProcessor is matching
 * 3. The IProcessor, evaluates the line of input if it is a statement and
 *    adds the result to the application context.
 * 4. The IProcessor, generates the answer if the line of input is a query
 *    and prints it to the console.  
 * 
 * 5. If no matching IProcessor is found the error message is printed to console.
 * 
 * @author abha
 *
 */
public class Application {
		
	Context context = new Context();
	String errorString = "I have no idea what you are talking about";

	/**
	 * Matches the appropriate processor with the expression
	 * and evaluates or prints the answer
	 * 
	 * @param expression
	 */
	public void process(String expression){		
		IProcessor processor = new SimpleStatementProcessor();
		boolean matched = false;
		if(processor.matchesMyType(expression)){
			matched = true;
			try {
				processor.evaluate(expression, context);
			} catch (GalacticParseException | GalacticInsufficientInformationException
					| GalacticValidationException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		processor = new CreditsStatementProcessor();
		if(processor.matchesMyType(expression)){
			matched = true;
			try {
				processor.evaluate(expression, context);
			} catch (GalacticParseException | GalacticInsufficientInformationException
					| GalacticValidationException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		
		IQueryProcessor queryProcessor = new SimpleQueryProcessor();
		if(queryProcessor.matchesMyType(expression)){
			matched = true;
			try {
				System.out.println(queryProcessor.getAnswer(expression, context));
			} catch (GalacticParseException | GalacticInsufficientInformationException
					| GalacticValidationException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		
		queryProcessor = new CreditsQueryProcessor();
		if(queryProcessor.matchesMyType(expression)){
			matched = true;
			try {
				System.out.println(queryProcessor.getAnswer(expression, context));
			} catch (GalacticParseException | GalacticInsufficientInformationException
					| GalacticValidationException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		if(!matched){
			System.out.println(errorString);
		}
	}

	/**
	 * The entry point to the application.
	 * Assumption is user.dir is set to the correct location.
	 * And file input.txt is located in the folder input under user.dir
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		 Application app = new Application();
		 BufferedReader br = null;       
	     String line = "";	        
	    
    	 String filename = 	 System.getProperty("user.dir")+ "/input/input.txt";
    	 try {
			br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null){
				 if(line.trim().isEmpty())
					 continue;
				 app.process(line.trim());
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
        finally{
        	try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

}
