package http;

import java.nio.file.Path;

import http.Response.Status;
import http.exception.HTTPException;
import http.resource.ResourceResolver;
import utils.Utils;

public class RequestHandler {
	
	ResourceResolver resolver = new ResourceResolver(".");
	MIMEResolver mime = new MIMEResolver();
	
	public void handle(Request request, Response response) {
			
		try {			
			Path resource = resolver.getResource(request.getContextPath());
			
			response.setContentType(mime.getContentType(Utils.getExtension(resource)));
			
			Utils.write(response.getOutputStream(), resource);
			
			response.setStatus(Status.OK);
		} catch (HTTPException e) {
			e.printStackTrace();
			response.error(e);
		}		
	}
	
}
