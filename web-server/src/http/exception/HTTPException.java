package http.exception;

import http.Response;

@SuppressWarnings("serial")
public abstract class HTTPException extends Exception{
	
	
	public HTTPException() {
		super();
	}

	public HTTPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HTTPException(String message, Throwable cause) {
		super(message, cause);
	}

	public HTTPException(String message) {
		super(message);
	}

	public HTTPException(Throwable cause) {
		super(cause);
	}

	public abstract Response.Status getResponseStatus();
	
}
