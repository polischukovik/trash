package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import http.exception.InternalServerError;

public class Utils {
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

	public static String getExtension(Path file) {
		String fileName = file.getFileName().toString();

		int i = fileName.lastIndexOf('.');
		int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

		if (i > p) {
			return fileName.substring(i+1);
		}
		return "";		
	}

	public static void write(OutputStream os, Path source) throws InternalServerError {
		try {
			InputStream is = Files.newInputStream(source);
			
			byte[] buffer = new byte[1024]; // Adjust if you want
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1){
				os.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			throw new InternalServerError("ERROR Exeption when writing response");
		}

	}
}
