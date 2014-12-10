package factoring;

import java.io.PrintWriter;
import java.math.BigInteger;

public class FactorThread implements Runnable{
	private BigInteger num;
	private BigInteger count;
	private BigInteger end;
	private boolean done;
	private final BigInteger TWO = new BigInteger("2");
	private PrintWriter out;
	private RelativelyPrimeFactorMaster m;
	/**
	 * Start must be an odd integer
	 * @param start
	 * @param end
	 */
	public FactorThread(RelativelyPrimeFactorMaster m, PrintWriter out, BigInteger num, BigInteger start, BigInteger end)
	{
		this.m = m;
		this.out = out;
		done = false;
		this.num = num;
		count = start;
		this.end = end;
	}
	
	public void setDone()
	{
		done = true;
	}
	
	public void run() {
		while (!done && count.compareTo(end) <= 0)
		{
			if (num.mod(count).equals(BigInteger.ZERO))
			{
				System.out.println("YES");
				BigInteger f1 = count; 
				BigInteger f2 = num.divide(count);

				m.setDone(f1, f2);
			}		
			count = count.add(TWO);
		}	
	}
}
