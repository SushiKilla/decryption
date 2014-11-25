package server;

import java.io.IOException;

public class Client {
	public static void main(String[] args) throws IOException {
		ClientThread clientThread = new ClientThread("127.0.0.1", 8888);
		clientThread.start();
	}
}