package p23_3;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueInterface {
	
	public static void main(String[] args) {
		ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(10);
		ConsumerBlocking c = new ConsumerBlocking(q, 100);
		ProviderBlocking p = new ProviderBlocking(q, "Items", 100);
		Thread one = new Thread(c);
		Thread two = new Thread(p);
		one.start();
		two.start();
	}
	
}
