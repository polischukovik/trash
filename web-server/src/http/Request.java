package http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import utils.Utils;

public class Request {
	/*
	 *  GET /hello.htm HTTP/1.1
		User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)
		Host: www.tutorialspoint.com
		Accept-Language: en-us
		Accept-Encoding: gzip, deflate
		Connection: Keep-Alive
	 */
	
	public HttpClient client;
	private Method method;
	private String contextPath;
	private String protocol;
	private Map<String, String> header = new HashMap<>();
	
	public static enum Method {
		GET, POST, PUT;
	}	
	
	public Request (String message, HttpClient client) {
		this.client = client;
		
		Queue<String> lines = new LinkedList<>(Arrays.asList(message.split(Utils.NEW_LINE_PATTERN)));

		parseCaption(lines.poll());		
		
		while(!lines.isEmpty()) {
			parseHeader(lines.poll());
		}		
		
	}
	
	private void parseHeader(String headerEntry) {
		String[] arr = headerEntry.split(":", 2);
		
		if(arr == null) {
			System.out.println();
		}
		
		if(arr.length == 2) {
			header.put(arr[0], arr[1]);
		} else {
			System.out.println("Cannot parse header: " + headerEntry);
		}
	}

	public void parseCaption(String caption) {
		String[] token = caption.split(" ");
		
		if(token.length == 3) {
			this.method = Method.valueOf(token[0]);
			this.contextPath = token[1];
			this.protocol = token[2];
		}
	}

	public Method getMethod() {
		return method;
	}

	public String getContextPath() {
		return contextPath;
	}

	public String getProtocol() {
		return protocol;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	@Override
	public String toString() {
		final StringBuilder headers = new StringBuilder();
		header.forEach((k, v) -> headers.append("\n" + k + ": " + v));
		return "Request [ method=" + method + " contextPath=" + contextPath + " protocol=" + protocol + " header="
				+ headers + "]\n";
	}
	
	
}
