package com.tw.galaxyguide.abhajoshi.processor.exception;

public class GalacticValidationException extends Exception{
	
	private static final long serialVersionUID = 5146223051919334821L;

	public GalacticValidationException(String message){
		super(message);
	}
	
	public GalacticValidationException(Exception ex){
		super(ex);
	}

}
