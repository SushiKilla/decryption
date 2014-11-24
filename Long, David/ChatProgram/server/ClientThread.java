package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

public class ClientThread extends Thread {
	private GUI gui;
	
	private Socket socket;
	
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	private Queue<String> postQueue;
	private Queue<String[]> pmQueue;
	
	private String username;
	
	private boolean attemptJoin;
	private boolean requestUserList;
	
	public ClientThread(String serverAddress, int port) throws IOException {
		gui = new GUI(this);
		
		socket = new Socket(serverAddress, port);
		
		socketIn = new Scanner(socket.getInputStream());
		socketOut = new PrintWriter(socket.getOutputStream());
	}
	
	public void join(String username) throws IOException {
		
		socketOut.flush();
	}
	
	public void postMessage(String message) throws IOException {
		postQueue.add(message);
	}
	
	public void sendPrivateMessage(String recepient, String message) throws IOException {
		pmQueue.add(new String[] {recepient, message});
	}
	
	public void requestUserList() throws IOException {
		requestUserList = true;
	}
	
	@Override
	public void run() {
		for(String post : postQueue)
			socketOut.printf("PM %s %s %d\n", post);
		
		for(String[] pm : pmQueue)
			socketOut.printf("PM %s %s %d\n", pm);
		
		if(requestUserList)
			socketOut.printf("JOIN %s\n", username);
		
		if(requestUserList)
			socketOut.printf("USER_LIST\n");
		
		socketOut.flush();
		
//		NEW_POST [user] [message] [timestamp]		New message has been posted
//		NEW_ PM [user] [message] [timestamp]		New private message
//		USER_JOIN [user] [timestamp]				New user joined
//		USER_LEAVE [user] [timestamp]				Some user left the room
//		ERROR [message]								Some error sent as described above
		while(socketIn.hasNextLine()) {
			String[] nextLine = socketIn.nextLine().trim().toUpperCase().split(" ");
			
			switch(nextLine[0]) {
			case "NEW_POST":
				break;
			}
		}
	}
}