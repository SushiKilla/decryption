package server;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {
		ClientThread clientThread = new ClientThread("10.5.100.90", 8888);
		clientThread.start();
	}
}