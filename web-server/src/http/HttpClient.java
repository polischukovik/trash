package http;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

import net.Client;
import utils.StringUtils;

public class HttpClient extends Client{
	private static RequestHandler requestHandler = new RequestHandler();
	
	private StringBuilder requestMessage;

	protected HttpClient(Socket socket, Consumer<Client> onConnected, Consumer<Client> onDisconnect)
			throws IOException {
		super(socket, onConnected, onDisconnect);

		requestMessage = new StringBuilder();
		
	}

	@Override
	protected void receive(String string) {
		
		requestMessage.append(string);
		
		if(string.matches(StringUtils.NEW_LINE_PATTERN)) {			
			onRequestReady();
		}
	}
	
	private void onRequestReady() {
		Request request = new Request(requestMessage.toString(), this);
		Response response = new Response(request);
		
		System.out.println("========= HTTP Request received =========");
		System.out.println(requestMessage);
		
		try {
			requestHandler.handle(request, response);
			
			response.send(this);
		} catch (IllegalStateException e) {
			System.out.println("ERROR: Could not handle request");
			e.printStackTrace();
		}
		
		disconnect();
	}

}
