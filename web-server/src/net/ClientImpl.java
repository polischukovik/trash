package net;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class ClientImpl extends Client {
	public ClientImpl(Socket socket, Consumer<Client> onConnected, Consumer<Client> onDisconnect) throws IOException {
		super(socket, onConnected, onDisconnect);
	}
	
	@Override
	public void receive(String string) {
		System.out.println(string);
	}	
}
