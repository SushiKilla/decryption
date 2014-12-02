package server;
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
	
	public boolean isUniqueUsername(String username) {
		for(ClientHandlerThread clientHandler : clientHandlers) {
			if(clientHandler.username != null && clientHandler.username.equals(username)) {
				return false;
			}
		}
		
		return true;
		
//		return !getUsernames().contains(username);
	}
	
	public void userJoined(String username) {
		for(ClientHandlerThread clientHandler : clientHandlers) {
			clientHandler.sendUserJoin(username);
		}
	}
	
	public void userQuit(String username) {
		int removeIndex = -1;
		for(int i = 0; i < clientHandlers.size(); ++i) {
			ClientHandlerThread clientHandler = clientHandlers.get(i);
			if(clientHandler.username != null && clientHandler.username.equals(username)) {
				removeIndex = i;
			}
		}
		
		if(removeIndex != -1)
			clientHandlers.remove(removeIndex);
		
		for(ClientHandlerThread clientHandler : clientHandlers) {
			clientHandler.sendUserQuit(username);
		}
	}
	
	public void broadcastMessage(String sender, String message) throws IOException {
		for(ClientHandlerThread clientHandler : clientHandlers) {
			clientHandler.sendPost(sender, message);
		}
	}
	
	public void privateMessage(String sender, String recipient, String message) throws IOException {
		for(ClientHandlerThread clientHandler : clientHandlers) {
			if(clientHandler.username != null && clientHandler.username.equals(recipient)) {
				clientHandler.sendPrivateMessage(sender, message);
			}
		}
	}
	
//	public List<String> getUsernames() {
//		List<String> usernames = new ArrayList<>();
//		for(ClientHandlerThread clientHandler : clientHandlers) {
//			if(clientHandler.username != null)
//				usernames.add(clientHandler.username);
//		}
//		
//		return usernames;
//	}
	
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