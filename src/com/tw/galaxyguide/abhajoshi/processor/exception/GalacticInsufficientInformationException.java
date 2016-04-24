package com.tw.galaxyguide.abhajoshi.processor.exception;

public class GalacticInsufficientInformationException extends Exception{

	private static final long serialVersionUID = 2506222949583531993L;

	public GalacticInsufficientInformationException(String message){
		super(message);
	}
	
	public GalacticInsufficientInformationException(Exception ex){
		super(ex);
	}
}
