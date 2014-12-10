package factoring;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) throws IOException {
		
		//Server: 10.5.100.168:8887
		//String[] address = JOptionPane.showInputDialog("Enter the server's address (IP:port)").split(":");
		
		ClientThread clientThread = new ClientThread("10.5.100.168", 8887);
		clientThread.start();
	}
}
