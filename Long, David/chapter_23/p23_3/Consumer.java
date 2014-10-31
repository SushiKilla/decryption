package p23_3;

public class Consumer implements Runnable{

	SynchronizedQueue<String> q;
	int iter;
	
	public Consumer(SynchronizedQueue<String> queue, int iterations)
	{
		q = queue; iter = iterations;
	}
	
	public void run() {
		
	}

}
