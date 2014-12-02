package client;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws IOException {
		String[] address = JOptionPane.showInputDialog("Enter the server's address (IP:port)").split(":");
		
		ClientThread clientThread = new ClientThread(address[0], Integer.parseInt(address[1]));
		clientThread.start();
	}
}