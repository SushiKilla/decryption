package p23_3;

public class Consumer implements Runnable{

	SynchronizedQueue<String> q;
	int iter;
	
	public Consumer(SynchronizedQueue<String> queue, int iterations)
	{
		q = queue; iter = iterations;
	}
	
	public void run() {
		try
		{
			while (iter > 0)
			{
				String a = q.remove();
				System.out.println("Consumer consumed a String: " + a + ", SIZE: " + q.size());
				iter--;
			}
		}
		catch (InterruptedException e)
		{
			System.out.println("Consumer interupted");
		}

	}

}
