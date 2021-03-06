package factoring;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientThread extends Thread {
	private Socket socket;
	private InputStream inStream;
	private Scanner socketIn;
	private PrintWriter socketOut;
	
	private boolean done = false;
	
	
	private BigInteger num;
	private BigInteger count;
	private BigInteger end;
	private final BigInteger TWO = new BigInteger("2");
	
	private RelativelyPrimeFactorMaster worker;
	
	public ClientThread(String serverAddress, int port) throws IOException {		
		socket = new Socket(serverAddress, port);
		
		inStream = socket.getInputStream();
		
		socketIn = new Scanner(inStream);
		socketOut = new PrintWriter(socket.getOutputStream());
	}

	public void run() {
		try
		{
			while (!done)
			{
				while(inStream.available() > 0) {
					String[] nextLine = socketIn.nextLine().trim().split(" ");
					System.out.println(Arrays.toString(nextLine));
					
					switch(nextLine[0]) {
					case "ASSIGN_WORK":
						num = new BigInteger(nextLine[1]);
						count = new BigInteger(nextLine[2]);
						end = new BigInteger(nextLine[3]);
						int numWorkers = 4;
						worker = new RelativelyPrimeFactorMaster(socketOut, num, count, end, numWorkers);
						worker.factor();
						break;
						
					case "FOUND":
						worker.setDone();
						worker = null;
						break;
					}
				}		
			}
		}
		catch(IOException e)
		{
			
		}
	}
}
