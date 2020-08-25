//----------------Exception class By Madhav---------------

package com.project.exception;

public class CompareServiceException extends RuntimeException {

	public CompareServiceException() {
		super();
	}

	public CompareServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CompareServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CompareServiceException(String arg0) {
		super(arg0);
	}

	public CompareServiceException(Throwable arg0) {
		super(arg0);
	}
	
}
