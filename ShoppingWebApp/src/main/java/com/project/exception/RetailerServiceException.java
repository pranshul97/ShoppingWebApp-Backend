//-----------RetailerServiceException by bhavya-------------------------------------------------------------------------------------
package com.project.exception;

public class RetailerServiceException extends RuntimeException {

	public RetailerServiceException() {
		super();
		
	}

	public RetailerServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public RetailerServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RetailerServiceException(String message) {
		super(message);
		
	}

	public RetailerServiceException(Throwable cause) {
		super(cause);
		
	}
	
	
}
