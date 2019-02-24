package http;

import java.io.IOException;
import java.io.OutputStream;

import exception.HTTPException;
import exception.InternalServerError;
import exception.NotFoundException;
import http.Response.Status;

public class RequestHandler {
	
	FileResponseProvider provider = new FileResponseProvider("");
	
	public void handle(Request request, Response response) {
		
		String responseText = "";
		
		try {
			responseText = provideResponse(request.getContextPath());
			
			write(response, responseText);
			
			response.setStatus(Status.OK);
		} catch (HTTPException e) {
			response.error(e);
			e.printStackTrace();
		}
		
	}

	private String provideResponse(String forPath) throws NotFoundException {
		return provider.provide(forPath);
	}
	
	private static void write(Response response, String responseText) throws InternalServerError {
		OutputStream os = response.getOutputStream();
		
		char[] arr = responseText.toCharArray();
		
		for(int i = 0; i < arr.length; i++) {
			try {
				os.write(arr[i]);
			} catch (IOException e) {
				throw new InternalServerError("ERROR Exeption when writing response");
			}
		}
	}
}
