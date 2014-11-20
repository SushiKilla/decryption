package factoringThreads;

import java.math.BigInteger;
import java.util.ArrayList;

public class RelativelyPrimeFactorMaster {

	ArrayList<FactorThread> factors = new ArrayList<FactorThread>();
	ArrayList<Thread> threads = new ArrayList<Thread>();
	BigInteger one, two, num;
	private final long PASS;
	private boolean done;
	int workers;
	long timeIn;
	
	/**
	 * N must have ONLY two factors
	 * Workers must be at LEAST 1 but cannot be bigger than N
	 * @param n  = a relatively prime Integer
	 * @param workers = number of workers
	 */
	public RelativelyPrimeFactorMaster(BigInteger n, int workers)
	{
		PASS = (long)(Math.random()*100000000);		
		num = n;
		this.workers = workers;
		done = false;
	}
	
	public void factor()
	{
		if (done) return;
		timeIn = System.currentTimeMillis();
		
		if (num.mod(new BigInteger("2")).equals(BigInteger.ZERO))
		{
			one = new BigInteger("2");
			two = num.divide(one);
			setDone(PASS, one, two);
		}
		else
		{
			BigInteger previous = new BigInteger("3");
			
			BigInteger increment = BigIntSqRoot.bigIntSqRootCeil(num).divide(new BigInteger(("" + workers)));
			if (increment.mod(new BigInteger("2")).equals(BigInteger.ONE))
				increment.add(BigInteger.ONE);
			
			for (int i = 0; i < workers; i++)
			{
				FactorThread f = new FactorThread(this, num, previous, previous.add(increment), PASS);
				factors.add(f);
				previous = previous.add(increment);
			}
			for (int i = 0; i < factors.size(); i++)
				threads.add(new Thread(factors.get(i)));
			for (Thread t: threads) t.start();
		}
	}
	
	public void setDone(long key, BigInteger f1, BigInteger f2)
	{
		if (PASS != key) return;
		
		one = f1;
		two = f2;
		
		for (FactorThread t: factors)
			t.setDone();
		
		System.out.println("This took " + (System.currentTimeMillis()-timeIn) + " milliseconds");
		System.out.println(num + " was factored by " + factors.size() + " threads.");
		System.out.println(num + "'s factors are " + one + " and " + two + ".");
		
		done = true;
	}
	
	public BigInteger getFirstFactor()
	{
		return one;
	}
	
	public BigInteger getSecondFactor()
	{
		return two;
	}
}
