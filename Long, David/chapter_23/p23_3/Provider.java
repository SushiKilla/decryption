package p23_3;

public class Provider implements Runnable{
	SynchronizedQueue<String> q;
	String add;
	int iter;
	
	public Provider(SynchronizedQueue<String> queue, String toAdd, int iterations)
	{
		q = queue; add = toAdd; iter = iterations;
	}
	
	public void run() {
		
	}

}
