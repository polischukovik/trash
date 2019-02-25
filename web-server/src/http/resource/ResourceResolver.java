package http.resource;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import http.exception.NotFoundException;

public class ResourceResolver {
	private String workDir = ".";
	private String defaultPath = "index.html";
	private LinkOption followLinks = LinkOption.NOFOLLOW_LINKS;
	
	
	public ResourceResolver(String workDir){
		this.workDir = workDir;
	}
	
	private String resolveEmpty(String contextPath) {
		return "".equals(contextPath) ? defaultPath : contextPath;
	}
	
	private String stripSlash(String path) {
		return path.startsWith("/") ? path.substring(1) : path ;
	}
	
	public Path resolve(String contextPath) throws NotFoundException{		
		String path = resolveEmpty(stripSlash(contextPath));
		Path work =  Paths.get(workDir).toAbsolutePath();
		
		System.out.println("Work directory: " + work);
		Path file = work.resolve(path);
		System.out.println("Resolving file: " + file);
		
		if(Files.notExists(file, followLinks)){
			throw new NotFoundException("Page not found");			
		}				
		
		return file;
	}

	public Path getResource(String contextPath) throws NotFoundException {
		String path = resolveEmpty(stripSlash(contextPath));		
		return resolve(path);
	}			
	
}
