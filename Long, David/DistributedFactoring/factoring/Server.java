package factoring;

import java.io.IOException;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerThread serverThread = new ServerThread(8888);
		serverThread.start();
	}
}
