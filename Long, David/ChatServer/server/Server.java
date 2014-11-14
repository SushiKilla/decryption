package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private final static int serverNum = 1111;
	
	public static void main(String[] args) {
		try
		{
			ServerSocket serverSocket = new ServerSocket(serverNum);
		    
		    //create:
		    ArrayList<Socket> clientSockets = new ArrayList<Socket>(); //serverSocket.accept() to accept a client;
		    //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		    //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		    
		    
		    ArrayList<Thread> threads = new ArrayList<Thread>();
		    ArrayList<User> users = new ArrayList<User>();
		    
		    try
		    {
		    	boolean done = false;
		    	
		    	while (!done)
		    	{
		    		//accept client
		    		Socket c = serverSocket.accept();
		    		clientSockets.add(c);
		    		
		    		//create thread to deal with client
		    		threads.add(new ClientThread("", serverNum));
		    		
		    		
		    	}
		    	
		    	
		    	
		    }
		    finally
		    {
		    	serverSocket.close();
		    	//clientSocket.close();
		    }
		}
		catch (IOException e)
		{
			System.out.println("Print Writer and Buffered Reader error.");
		}
	}
	
	public static String createErrorMessage()
	{
		return "ERROR " + ""; 
	}
	
	
	
	
}
