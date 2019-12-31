package com.yulece.app.management.commons.utils.exception;

public class ParamException extends RuntimeException{
	
	
	  /**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public ParamException() {
	        super();
	    }

	    public ParamException(String message) {
	        super(message);
	    }

	    public ParamException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public ParamException(Throwable cause) {
	        super(cause);
	    }

	    protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
}
