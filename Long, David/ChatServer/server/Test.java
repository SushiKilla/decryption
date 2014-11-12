package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Test {
	private static final String host = "java.sun.com";
	private static final int port = 80;
	private static final String resource = "/";
	public static void main(String[] args) throws UnknownHostException, IOException {
		
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
	}

}
