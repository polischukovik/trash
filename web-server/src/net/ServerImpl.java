package net;
import java.io.IOException;
import java.net.Socket;

public class ServerImpl extends Server {
	public ServerImpl(int port) throws IOException {
		super(port);		
	}
	
	@Override
	protected void createClient(Socket socket) throws IOException {
		new ClientImpl(socket, this::clientConnected, this::clientDisconencted);
	}
	
	@Override
	protected void clientDisconencted(Client client) {
		removeClient(client);
		System.out.println(String.format("%s has been disconnected", client));
	}
	
	@Override
	protected void clientConnected(Client client) {
		clients.add(client);
		System.out.println(String.format("%s has been connected ", client));
	}

}
