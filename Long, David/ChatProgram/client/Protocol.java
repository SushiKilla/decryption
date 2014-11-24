package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Protocol {
	private Socket s;
	private InputStream instream;
	private OutputStream outstream;
	private Scanner in;
	private PrintWriter out;
	
	public Protocol(Socket s) throws IOException
	{
		this.s = s;
		
		instream = s.getInputStream();
		outstream = s.getOutputStream();
		in = new Scanner(instream);
		out = new PrintWriter(outstream);
	}
	
	public String getNextResponse()
	{
		if (in.hasNextLine()) return in.nextLine();
		return "";
	}
	
	//Client >> Server commands
	public void post(String message)
	{
		out.print("POST " + message);
		out.flush();
	}
	public void list(int n)
	{
		out.print("LIST " + n);
		out.flush();
	}
	public void join(String name)
	{
		out.print("JOIN " + name);
		out.flush();
	}
	public void PM(String name, String message)
	{
		out.print("PM " + name + " " + message);
		out.flush();
	}
	public void requestUserList()
	{
		out.print("USER_LIST");
		out.flush();
	}
	
	public void disconnect() throws IOException
	{
		out.close();
		s.close();
	}
	
}
