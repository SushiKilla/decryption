package p23_3;

public class Provider implements Runnable{
	SynchronizedQueue<String> q;
	String s;
	int iter;
	
	public Provider(SynchronizedQueue<String> queue, String toAdd, int iterations)
	{
		q = queue; s = toAdd; iter = iterations;
	}
	
	public void run() {
		for (int i = 0; i < iter; i++)
		{
			try {
				q.add(s);
				System.out.println("Producer created a String: " + s + ", SIZE: " + q.size());
			} catch (InterruptedException e) {
				System.out.println("Provider interupted");
			}
		}
	}

}
