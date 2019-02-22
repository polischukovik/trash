import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

public class Client {
	private static int sequence = 0;
	
	private int id;
	private Socket socket;
	private PrintStream out;
	private Scanner in;
	private Thread inputListener, disconnectionListener;
	private Consumer<Client> onDisconnect, onConnected;

	private boolean disconnected = false;

	public Client(Socket socket, Consumer<Client> onConnected, Consumer<Client> onDisconnect) throws IOException {
		this.id = ++sequence;
		this.socket = socket;
		this.onDisconnect = onDisconnect;
		this.onConnected = onConnected;
		
		this.out = new PrintStream(socket.getOutputStream());
		this.in = new Scanner(socket.getInputStream());
		
		this.inputListener = new Thread(() -> {
			while(!disconnected) {
				receive(in.nextLine());
			}
		});
		
		this.disconnectionListener = new Thread(() -> {
			while(!disconnected) {
				if(socket.isClosed()) {
					disconnected = true;
					onDisconnect.accept(this);
				}
			}
		});
		
		
		inputListener.start();
		disconnectionListener.start();				
	}
	
	public void send(String string) {
		out.println(string);
	}	
	
	public void receive(String string) {
		System.out.println(string);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}
	
	
}
