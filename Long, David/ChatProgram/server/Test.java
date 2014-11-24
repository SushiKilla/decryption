package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Test {
	private static final String host = "java.sun.com";
	private static final int port = 80;
	private static final String resource = "/";
	public static void main(String[] args) throws UnknownHostException, IOException {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Hadi");
		names.add("Anurag");
		names.add("Arthur");
		names.add("Richard");
		names.add("David");
		names.add("Ryan");
		PrintWriter p = new PrintWriter(new File("H:/git-repositories/decryption/Long, David/"
				+ "ChatServer/server/MessageLog.txt"));
		MainInterface frame = new MainInterface(names, p);
		frame.setVisible(true);
		
		/*
		//open port
		Socket s = new Socket(host, port);
		
		//get streams
		InputStream instream = s.getInputStream();
		OutputStream outstream = s.getOutputStream();
		
		//turn streams into scanner and writer
		Scanner in = new Scanner(instream);
		PrintWriter out = new PrintWriter(outstream);
		
		//get stuff
		out.print("GET" + resource + "HTTP/1.0\n\n");
		out.flush();
		
		//read response
		while (in.hasNextLine())
		{
			String input = in.nextLine();
			System.out.println(input);
		}
		
		in.close();
		s.close();
		
		*/
	}

}
