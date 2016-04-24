package com.tw.galaxyguide.abhajoshi.processor.exception;

/**
 * 
 * @author abha
 *
 */
public class GalacticParseException extends Exception {

	private static final long serialVersionUID = 4027031061549807743L;

	public GalacticParseException(String message) {
		super(message);
	}

	public GalacticParseException(Exception ex) {
		super(ex);
	}
}
