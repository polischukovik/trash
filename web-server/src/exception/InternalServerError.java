package exception;

import http.Response;
import http.Response.Status;

@SuppressWarnings("serial")
public class InternalServerError extends HTTPException {
	
	public InternalServerError() {
		super();
	}

	public InternalServerError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InternalServerError(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalServerError(String message) {
		super(message);
	}

	public InternalServerError(Throwable cause) {
		super(cause);
	}

	@Override
	public Status getResponseStatus() {
		return Response.Status.INTERNAL_SERVER_ERROR;
	}

}
