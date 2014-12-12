package factoring;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ClientHandlerThread extends Thread {
	private ServerThread server;
	private Socket socket;
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	public ClientHandlerThread(ServerThread parentServer, Socket clientSocket) throws IOException {
		server = parentServer;
		socket = clientSocket;
		
		socketIn = new Scanner(socket.getInputStream());
		socketOut = new PrintWriter(socket.getOutputStream());
	}
	
	public void assignWork(BigInteger number, BigInteger start, BigInteger end)
	{
		socketOut.printf("ASSIGN_WORK %s %s %s\n", number.toString(), start.toString(), end.toString());
		socketOut.flush();
	}

	public void stopWork()
	{
		socketOut.println("FOUND");
		socketOut.flush();
	}
	
	@Override
	public void run() {
		try {
			while(socketIn.hasNextLine()) {
				String[] nextLine = socketIn.nextLine().trim().split(" ");
				System.out.println(Arrays.toString(nextLine));
				
				switch(nextLine[0]) {
				case "FOUND":
					System.out.println("Factors found: ");
					System.out.println(nextLine[1] + " and " + nextLine[2]);
					server.setDone();
					break;
				}
			}
		}
		catch(Exception e) {
			
		}
	}
}
