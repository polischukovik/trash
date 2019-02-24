package exception;

import http.Response;
import http.Response.Status;

@SuppressWarnings("serial")
public class NotFoundException extends HTTPException {

	
	
	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	@Override
	public Status getResponseStatus() {
		return Response.Status.NOT_FOUND;
	}

}
