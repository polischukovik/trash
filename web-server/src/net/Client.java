package net;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

import utils.Utils;

public abstract class Client {

	protected static int sequence = 0;
	protected int id;
	protected Socket socket;
	protected PrintStream out;
	protected Scanner in;
	protected Thread inputListener;
	protected Consumer<Client> onDisconnect;
	protected Consumer<Client> onConnected;

	protected Client(Socket socket, Consumer<Client> onConnected, Consumer<Client> onDisconnect) throws IOException {
		this.id = ++sequence;
		this.socket = socket;
		this.onDisconnect = onDisconnect;
		this.onConnected = onConnected;
		
		this.out = new PrintStream(socket.getOutputStream());
		this.in = new Scanner(socket.getInputStream());
		
		this.inputListener = new Thread(() -> {
			while(in.hasNextLine()) {
				try {
					receive(in.nextLine() + Utils.NEW_LINE);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					disconnect();
				}
			}
		});		
		
		inputListener.start();		
		this.onConnected.accept(this);
	}

	protected void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		onDisconnect.accept(this);		
	}
	
	protected abstract void receive(String string);
	public void send(String string) {
		out.print(string);
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
		ClientImpl other = (ClientImpl) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + "]";
	}

}