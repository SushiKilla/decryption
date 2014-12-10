package factoring;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class RelativelyPrimeFactorMaster {

	ArrayList<FactorThread> factors = new ArrayList<FactorThread>();
	ArrayList<Thread> threads = new ArrayList<Thread>();
	BigInteger one, two, num, start, end;
	PrintWriter out;
	int workers;
	
	/**
	 * N must have ONLY two factors
	 * Workers must be at LEAST 1 but cannot be bigger than N
	 * @param n  = a relatively prime Integer
	 * @param workers = number of workers
	 */
	public RelativelyPrimeFactorMaster(PrintWriter out, BigInteger n, BigInteger start, BigInteger end, int workers)
	{
		this.out = out;
		this.start = start; this.end = end;	
		num = n;
		this.workers = workers;
	}
	
	public void factor()
	{
		BigInteger previous = start;
		BigInteger increment = (end.subtract(start)).divide(new BigInteger("" + workers));
		if (previous.mod(new BigInteger("2")).equals(BigInteger.ZERO))
			previous = previous.subtract(BigInteger.ONE);
		if (increment.mod(new BigInteger("2")).equals(BigInteger.ONE))
			increment = increment.add(BigInteger.ONE);
		
		for (int i = 0; i < workers; i++)
		{
			FactorThread f = new FactorThread(this, out, num, previous, previous.add(increment));
			factors.add(f);
			previous = previous.add(increment);
		}
		for (int i = 0; i < factors.size(); i++)
			threads.add(new Thread(factors.get(i)));
		for (Thread t: threads) t.start();

	}
	
	public void setDone(BigInteger f1, BigInteger f2)
	{	
		one = f1;
		two = f2;
		
		for (FactorThread t: factors)
			t.setDone();
		
		out.println("FOUND " + f1.toString() + " " + f2.toString());
	}
	
	public void setDone()
	{
		for (FactorThread t: factors)
			t.setDone();
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
