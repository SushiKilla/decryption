package p23_3;

import java.util.concurrent.ArrayBlockingQueue;

public class ProviderBlocking implements Runnable{
	ArrayBlockingQueue<String> q;
	String s;
	int iter;
	
	public ProviderBlocking(ArrayBlockingQueue<String> queue, String toAdd, int iterations)
	{
		q = queue; s = toAdd; iter = iterations;
	}
	
	public void run() {
		try
		{
			while (iter > 0)
			{
				q.put(s);
				System.out.println("Producer created a String: " + s + ", SIZE: " + q.size());
				iter--;
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Provder interrupted");
		}
	}

}
