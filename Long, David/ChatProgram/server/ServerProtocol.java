package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServerProtocol {
	private InputStream instream;
	private OutputStream outstream;
	private Scanner in;
	private PrintWriter out;

	public ServerProtocol() {
	}

	public String getNextResponse() {
		if(in.hasNextLine())
			return in.nextLine();
		
		return "";
	}

	// Client >> Server commands
	public void post(String message) {
		out.print("POST " + message);
		out.flush();
	}

	public void list(int n) {
		out.print("LIST " + n);
		out.flush();
	}

	public void join(String name) {
		out.print("JOIN " + name);
		out.flush();
	}

	public void PM(String name, String message) {
		out.print("PM " + name + " " + message);
		out.flush();
	}

	public void requestUserList() {
		out.print("USER_LIST");
		out.flush();
	}
}