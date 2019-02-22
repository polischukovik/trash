import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ServerImpl {
	private static ServerImpl instance;
	
	private boolean requestedStop = false;
	private ServerSocket server;
	private Set<Client> clients;
	private Thread listeningThread;
			
	private ServerImpl(int port) throws IOException {
		server = new ServerSocket(port);
		clients = new HashSet<Client>();
				
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
	
	public static void create(int port) throws IOException {
		instance = new ServerImpl(port);
	}
	
	public static void dispose() {
		ServerImpl.instance.stop();
	}

	private void stop() {
		requestedStop = true;
	}
	
	private void clientDisconencted(Client client) {
		removeClient(client);
	}
	
	private void clientConnected(Client client) {
		clients.add(client);
		System.out.println(String.format("Client %d has been connected ", client.getId()));
	}
	
	private void createClient(Socket socket) throws IOException {
		new Client(socket, this::clientConnected, this::clientDisconencted);
	}
	
	private void removeClient(Client client) {
		clients.remove(client);
	}

}
