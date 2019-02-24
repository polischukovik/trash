package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class StringUtils {
	public final static String NEW_LINE = "\n";
	public final static String NEW_LINE_PATTERN = "\r?\n";
	//Wed, 22 Jul 2009 19:15:56 GMT
	public final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz")
			.withZone(ZoneId.systemDefault());
	
	public static String readFile(String path) throws IOException {
		StringBuilder sb = new StringBuilder();		
		BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("UTF-8"));
		
				String line = null;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    
	    return sb.toString();
	}
}
