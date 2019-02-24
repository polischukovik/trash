package net;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;


public abstract class Server {

	protected static Server instance;
	protected boolean requestedStop = false;
	protected ServerSocket server;
	protected Set<Client> clients;
	protected Thread listeningThread;	

	public static void dispose() {
		Server.instance.stop();
	}

	protected Server(int port) throws IOException {
		server = new ServerSocket(port);
		clients = new HashSet<Client>();
		instance = this;
				
		listeningThread = new Thread(() -> {
			System.out.println("Starting server listener");
			while(!requestedStop) {			
				try {					
					Socket clientSocket = server.accept();					
					createClient(clientSocket);
					
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}		
			System.out.println("Stopping server listener...");			
		});
		
		listeningThread.start();

		System.out.println("Server has started on port " + port);
	}

	private void stop() {
		requestedStop = true;
	}
	
	protected void removeClient(Client client) {
		clients.remove(client);
	}
	
	protected abstract void createClient(Socket socket) throws IOException;
	protected abstract void clientConnected(Client client);
	protected abstract void clientDisconencted(Client client);
}