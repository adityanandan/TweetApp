package com.tweetapp.exception;

public class ResourceAlreadyPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyPresentException(String string) {
		super(string);
	}

}
