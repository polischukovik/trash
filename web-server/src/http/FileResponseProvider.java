package http;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import exception.NotFoundException;
import utils.StringUtils;

public class FileResponseProvider {
	private String workDir = ".";
	private String defaultPath = "index.html";
	private LinkOption followLinks = LinkOption.NOFOLLOW_LINKS;
	
	
	public FileResponseProvider(String workDir){
		this.workDir = workDir;
	}
	
	private String resolveEmpty(String contextPath) {
		return "/".equals(contextPath) ? defaultPath : contextPath;
	}
	
	private String stripSlash(String path) {
		return path.startsWith("/") ? path.substring(1) : path ;
	}
	
	public String provide(String contextPath) throws NotFoundException{		
		String path = resolveEmpty(stripSlash(contextPath));
		Path work =  Paths.get(workDir).toAbsolutePath();
		
		System.out.println("Work directory: " + work);
				
		System.out.println("Resolving file: " + work.resolve(path));
		
		if(Files.notExists(Paths.get(workDir).resolve(path), followLinks)){
			throw new NotFoundException("Page not found");			
		}
		
		String fileText = "";
		try {
			fileText = StringUtils.readFile(workDir + path);
		} catch (IOException e) {
			throw new InternalError("Error reading file", e);
		}
		
		return fileText;
	}
	
	
}
