package http;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MIMEResolver {
	Map<String, String> map = new HashMap<>();
	
	public MIMEResolver() {
		try {
			Files.lines(Paths.get(getClass().getResource("/mime.table").toURI()))
				.forEach((line) -> map.put(line.split("\t")[0], line.split("\t")[1]));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}		
	}
	
	public String getContentType(String ext) {
		return map.get(ext);
	}

}
