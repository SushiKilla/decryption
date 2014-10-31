package p23_3;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class SynchronizedQueue<E>{
	private int maxSize;
	ArrayList<E> queue;
	
	final Lock lock = new ReentrantLock();
	final Condition notFull  = lock.newCondition(); 
	final Condition notEmpty = lock.newCondition(); 
	
	/**
	 * Precondition size > 0
	 * @param size
	 */
	public SynchronizedQueue(int size)
	{
		if (size <= 0) throw new IllegalArgumentException();

		maxSize = size;
		queue = new ArrayList<E>();
	}
	
	public void add(E arg0) throws InterruptedException {
		lock.lock();
		try
		{
			while (queue.size() == maxSize)
				notFull.await();
			
			queue.add(arg0);
			notEmpty.signal();
		}
		finally
		{
			lock.unlock();
		}

	}
	public void remove() throws InterruptedException {
		lock.lock();
		try
		{
			while (queue.size() == 0)
				notEmpty.await();
			queue.remove(0);
			notFull.signal();
		}
		finally
		{
			lock.unlock();
		}
	}

	public int maxSize() {
		return maxSize;
	}
}
