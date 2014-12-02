package factoring;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientThread extends Thread {
	private Socket socket;
	private InputStream inStream;
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	public ClientThread(String serverAddress, int port) throws IOException {		
		socket = new Socket(serverAddress, port);
		
		inStream = socket.getInputStream();
		
		socketIn = new Scanner(inStream);
		socketOut = new PrintWriter(socket.getOutputStream());
	}

	public void run() {
		
	}
}
