package factoring;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import factoringThreads.BigIntSqRoot;

public class ServerThread extends Thread {
	
	private long timeIn = 0;
	
	private ServerSocket serverSocket;
	
	private List<ClientHandlerThread> clientHandlers = new ArrayList<>();
	
	public ServerThread(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}

	/**
	 * Precondition: NUM IS NON-EVEN RELATIVELY PRIME NUMBER
	 * @param num IS NON-EVEN RELATIVELY PRIME NUMBER
	 */
	public void assignWork(BigInteger num)
	{
		timeIn = System.currentTimeMillis();
		
		int workers = clientHandlers.size();
		BigInteger previous = new BigInteger("3");
		
		BigInteger increment = BigIntSqRoot.bigIntSqRootCeil(num).divide(new BigInteger(("" + workers)));
		if (increment.mod(new BigInteger("2")).equals(BigInteger.ZERO))
			increment = increment.add(BigInteger.ONE);
		
		for (int i = 0; i < workers; i++)
		{
			clientHandlers.get(i).assignWork(num, previous, previous.add(increment));
			previous = previous.add(increment);
		}
	}
	
	public void setDone()
	{
		System.out.println("It took " + (System.currentTimeMillis() - timeIn) + " ms");
		
		int workers = clientHandlers.size();
		for (int i = 0; i < workers; i++)
		{
			clientHandlers.get(i).stopWork();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				ClientHandlerThread clientHandler = new ClientHandlerThread(this, serverSocket.accept());
				clientHandler.start();
				
				clientHandlers.add(clientHandler);
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
