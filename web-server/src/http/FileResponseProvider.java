package http;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
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
	
	public String provide(String contextPath) throws NotFoundException{		
		String path = resolveEmpty(contextPath);
		
		System.out.println("Resolving file: " + Paths.get(path).toAbsolutePath());
		
		if(Files.notExists(Paths.get(workDir + path), followLinks)){
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
