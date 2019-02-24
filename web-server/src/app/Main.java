package app;
import java.io.IOException;

import http.HttpServer;

public class Main {

	public static void main(String[] args) throws IOException {
		int port = 5000;
		
		new HttpServer(port);				
	}

}
