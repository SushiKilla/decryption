package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	//private GUI gui;
	
	private Socket socket;
	
	private BufferedReader socketIn;
	private BufferedWriter socketOut;
	
	public ClientThread(String serverAddress, int port) throws IOException {
		//gui = new GUI(this);
		
		socket = new Socket(serverAddress, port);
		
		socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	public void join(String username) throws IOException {
		socketOut.write(String.format("JOIN %s\n", username));
		socketOut.flush();
	}
	
	public void postMessage(String message) throws IOException {
		socketOut.write(String.format("POST %s\n", message));
		socketOut.flush();
	}
	
	public void sendPrivateMessage(String recepient, String message) throws IOException {
		socketOut.write(String.format("PM %s %s %d\n", recepient, message));
	}
}
