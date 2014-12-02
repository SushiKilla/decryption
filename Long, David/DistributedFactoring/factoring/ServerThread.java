package factoring;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread extends Thread {
	private ServerSocket serverSocket;
	
	private List<ClientHandlerThread> clientHandlers = new ArrayList<>();
	
	public ServerThread(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
	public void userJoined() {
		for(ClientHandlerThread clientHandler : clientHandlers) {
			clientHandler.sendUserJoin();
		}
	}
	
	public void userQuit() {
		
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				ClientHandlerThread clientHandler = new ClientHandlerThread(this, serverSocket.accept());
				clientHandler.start();
				
				clientHandlers.add(clientHandler);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
