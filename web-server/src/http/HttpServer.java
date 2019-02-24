package http;

import java.io.IOException;
import java.net.Socket;

import net.Client;
import net.Server;

public class HttpServer extends Server {

	public HttpServer(int port) throws IOException {
		super(port);
	}

	@Override
	protected void createClient(Socket socket) throws IOException {
		new HttpClient(socket, this::clientConnected, this::clientDisconencted);
	}

	@Override
	protected void clientConnected(Client client) {
		System.out.println(client + " connected");
	}

	@Override
	protected void clientDisconencted(Client client) {
		System.out.println(client + " disconnected");
	}

}
