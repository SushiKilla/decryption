package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ClientHandlerThread extends Thread {
	private ServerThread server;
	
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	public String username;
	
	private boolean clientConnected = true;
	
	public ClientHandlerThread(ServerThread parentServer, Socket clientSocket) throws IOException {
		server = parentServer;
		
		socket = clientSocket;
		
		socketIn = new Scanner(socket.getInputStream());
		socketOut = new PrintWriter(socket.getOutputStream());
	}
	
	public void sendUserJoin(String username) {
		socketOut.printf("USER_JOIN %s\n", username);
		socketOut.flush();
	}
	
	public void sendUserQuit(String username) {
		socketOut.printf("USER_LEAVE %s\n", username);
		socketOut.flush();
	}
	
	public void sendPost(String sender, String message) throws IOException {
		socketOut.printf("NEW_POST %s %s %d\n", sender, message, System.currentTimeMillis());
		socketOut.flush();
	}
	
	public void sendPrivateMessage(String sender, String message) throws IOException {
		socketOut.printf("NEW_PM %s %s %d\n", sender, message, System.currentTimeMillis());
		socketOut.flush();
	}
	
	public void sendError(String message) {
		socketOut.printf("ERROR %s\n", message);
		socketOut.flush();
	}
	
	@Override
	public void run() {
		try {
			while(clientConnected && socketIn.hasNextLine()) {
				String[] nextLine = socketIn.nextLine().trim().split(" ");
				System.out.println(Arrays.toString(nextLine));
				
				switch(nextLine[0]) {
				case "JOIN":
					if(server.isUniqueUsername(nextLine[1])) {
						username = nextLine[1];
						server.userJoined(username);
					} else
						sendError("USERNAME_TAKEN");
					break;
				
				case "POST":
					if(nextLine.length == 2) {
						if(username != null)
							server.broadcastMessage(username, nextLine[1]);
						else
							sendError("USERNAME_NOT_SET");
					} else {
						sendError("SYNTAX_ERROR");
					}
					break;
					
				case "PM":
					if(nextLine.length == 3) {
						if(username != null)
							if(!server.isUniqueUsername(nextLine[1]))
								server.privateMessage(username, nextLine[1], nextLine[2]);
							else
								sendError("NO_SUCH_USER");
						else
							sendError("USERNAME_NOT_SET");
					} else {
						sendError("SYNTAX_ERROR");
					}
					break;
					
//				case "USER_LIST":
//					StringBuilder usernameString = new StringBuilder();
//					for(String name : server.getUsernames()) {
//						usernameString.append(name + " ");
//					}
					
//					socketOut.printf("USER_LIST %s\n", usernameString);
//					for(String name : server.getUsernames())
//						sendUserJoin(name);
//					break;
					
				case "QUIT":
					clientConnected = false;
					server.userQuit(username);
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}