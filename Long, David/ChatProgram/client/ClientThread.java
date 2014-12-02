package client;

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
	private GUI gui;
	
	private Socket socket;
	
	//*****
	private InputStream inStream;
	
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	private Queue<String> postQueue = new ConcurrentLinkedQueue<>();
	private Queue<String[]> pmQueue = new ConcurrentLinkedQueue<>();
	
	private String username;
	
	private boolean attemptJoin;
//	private boolean requestUserList;
	private boolean quit;
	
	public ClientThread(String serverAddress, int port) throws IOException {
		gui = new GUI(this);
		gui.setVisible(true);
		
		socket = new Socket(serverAddress, port);
		
		inStream = socket.getInputStream();
		
		socketIn = new Scanner(inStream);
		socketOut = new PrintWriter(socket.getOutputStream());
	}
	
	public void join(String username) throws IOException {
		this.username = username;
		attemptJoin = true;
	}
	
	public void postMessage(String message) throws IOException {
		postQueue.add(URLEncoder.encode(message));
	}
	
	public void sendPrivateMessage(String recepient, String message) throws IOException {
		pmQueue.add(new String[] {recepient, URLEncoder.encode(message)});
	}
	
//	public void requestUserList() throws IOException {
//		requestUserList = true;
//	}
	
	public void disconnect() {
		quit = true;
	}
	
	@Override
	public void run() {
		try {
			while(!quit) {
				while(!postQueue.isEmpty())
					socketOut.printf("POST %s\n", postQueue.poll());
				
				while(!pmQueue.isEmpty())
					socketOut.printf("PM %s %s\n", (Object[]) pmQueue.poll());
				
				if(attemptJoin)
					socketOut.printf("JOIN %s\n", username);
				
//				if(requestUserList)
//					socketOut.printf("USER_LIST\n");
				
				attemptJoin = false;
//				requestUserList = false;
				
				socketOut.flush();
						
	//			 NEW_POST [user] [message] [timestamp]		New message has been posted
	//			 NEW_PM [user] [message] [timestamp]		New private message
	//			 USER_JOIN [user] [timestamp]				New user joined
	//			 USER_LEAVE [user] [timestamp]				Some user left the room
	//			 ERROR [message]							Some error sent as described above
				while(inStream.available() > 0) {
					String[] nextLine = socketIn.nextLine().trim().split(" ");
					System.out.println(Arrays.toString(nextLine));
					
					switch(nextLine[0]) {
					case "NEW_POST":
						gui.newPost(nextLine[1], nextLine[2], Long.parseLong(nextLine[3]));
						break;
					
//					case "NEW_PM":
//						gui.newPost(nextLine[1], nextLine[2], Long.parseLong(nextLine[3]));
//						break;
//					
//					case "USER_JOIN":
//						gui.userJoin(nextLine[1]);
//						break;
//						
//					case "USER_LEAVE":
//						gui.userLeave(nextLine[1]);
//						break;
//					
//					case "USER_LIST":
//						String[] users = new String[nextLine.length - 1];
//						System.arraycopy(nextLine, 1, users, 0, users.length);
//						gui.setUserList(users);
//						break;
						
					case "ERROR":
						gui.error(nextLine[1]);
						break;
					}
				}
			}
			
			socketOut.printf("QUIT\n");
		} catch(IOException ioe) {}
	}
}