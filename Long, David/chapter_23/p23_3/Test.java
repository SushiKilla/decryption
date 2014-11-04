package p23_3;

public class Test {

	public static void main(String[] args) {
		SynchronizedQueue<String> q = new SynchronizedQueue<String>(10);
		Consumer c = new Consumer(q, 100);
		Provider p = new Provider(q, "Items", 100);
		Thread one = new Thread(c);
		Thread two = new Thread(p);
		
		one.start();
		two.start();
	}

}
