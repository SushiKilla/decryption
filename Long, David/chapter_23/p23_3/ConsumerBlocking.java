package p23_3;

import java.util.concurrent.ArrayBlockingQueue;

public class ConsumerBlocking implements Runnable{

	ArrayBlockingQueue<String> q;
	int iter;
	
	public ConsumerBlocking(ArrayBlockingQueue<String> queue, int iterations)
	{
		q = queue; iter = iterations;
	}
	
	public void run() {
		try
		{
			while (iter > 0)
			{
				String a = q.take();
				System.out.println("Consumer consumed a String: " + a + ", SIZE: " + q.size());
				iter--;
			}	
		}
		catch (InterruptedException e)
		{
			System.out.println("Consumer Interrupted");
		}
	}
}
