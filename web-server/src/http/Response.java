package http;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import exception.HTTPException;
import net.Client;
import utils.StringUtils;

public class Response {
	
	/**
	 *  HTTP/1.1 200 OK
		Date: Mon, 27 Jul 2009 12:28:53 GMT
		Server: Apache/2.2.14 (Win32)
		Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
		Content-Length: 88
		Content-Type: text/html
		Connection: Closed
	 */	
	
	private Request request;
	
	private String protocol;
	private Map<String, String> header = new HashMap<>();
	private StringBufferOutputStream out = new StringBufferOutputStream();
	private Status status;
	
	public Response(Request request) {
		this.request = request;
		this.protocol = request.getProtocol();	
	}
	
	public void send(Client client) {
		if(getStatus() == null)
			throw new IllegalStateException("ERROR: Response status was not set");		
		
		String raw = getRaw();
		client.send(raw);

		System.out.println("========= HTTP Response sent =========");
		System.out.println(raw);
	}
	
	public void error(HTTPException e) {
		header.clear();
		setStatus(e.getResponseStatus());
		getCaption();
	}
	
	private String getRaw() {
		fillHeader(out.toString());
		
		StringBuilder sb = new StringBuilder();
				
		sb.append(getCaption())
			.append(header.entrySet().stream()
				.map(e -> e.getKey() + ": " + e.getValue())
				.collect(Collectors.joining("\n")))
			.append(StringUtils.NEW_LINE)
			.append(StringUtils.NEW_LINE)
			.append(out.toString())
			.append(StringUtils.NEW_LINE);
		
		return sb.toString();
	}

	private String getCaption(){
		return String.format("%s %s %s\n", getProtocol(), getStatus().getCode(), getStatus().toString());
	}
	
	private void fillHeader(String body) {
		header.put("Date", StringUtils.DATE_FORMAT.format(LocalDateTime.now()));
		header.put("Server", "MyServer/2.1 (Win 32)");
		header.put("Last-Modified", StringUtils.DATE_FORMAT.format(LocalDateTime.now()));
		header.put("Content-Length", Integer.toString(body.length()));
		header.put("Content-Type", "text/html");
		header.put("Connection", "Closed");
	}

	public static enum Status{
		OK(200), NOT_FOUND(404), INTERNAL_SERVER_ERROR(500);
		
		private int code;
		Status(int code){
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}	
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Request getRequest() {
		return request;
	}

	public String getProtocol() {
		return protocol;
	}

	public OutputStream getOutputStream() {
		return out;
	}
	
	private static class StringBufferOutputStream extends OutputStream{

		StringBuffer buffer;
		
		public StringBufferOutputStream() {
			super();
			buffer = new StringBuffer();
		}

		@Override
		public void write(int b) throws IOException {
			buffer.append((char) b);			
		}

		@Override
		public String toString() {
			return buffer.toString();
		}
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", protocol=" + protocol + ", header=" + header + "]";
	}
	
}
