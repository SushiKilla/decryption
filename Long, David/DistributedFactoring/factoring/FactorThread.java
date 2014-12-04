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
	
	/**
	 * Start must be an odd integer
	 * @param start
	 * @param end
	 */
	public FactorThread(PrintWriter out, BigInteger num, BigInteger start, BigInteger end)
	{
		this.out = out;
		done = false;
		count = start;
		this.num = num;
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
				BigInteger f1 = count; 
				BigInteger f2 = num.divide(count);					
				out.println("FOUND " + f1.toString() + " " + f2.toString());	
				
			}
			
			count = count.add(TWO);
		}
		
	}

}
