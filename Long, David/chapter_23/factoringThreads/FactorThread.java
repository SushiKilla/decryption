package factoringThreads;

import java.math.BigInteger;

public class FactorThread implements Runnable{
	private BigInteger num;
	private BigInteger count;
	private BigInteger end;
	RelativelyPrimeFactorMaster master;
	private boolean done;
	private final BigInteger TWO = new BigInteger("2");
	private final long PASSWORD;
	
	/**
	 * Start must be an odd integer
	 * @param start
	 * @param end
	 */
	public FactorThread(RelativelyPrimeFactorMaster m, BigInteger num, BigInteger start, BigInteger end, long pass)
	{
		PASSWORD = pass;
		done = false;
		master = m;
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
				master.setDone(PASSWORD, f1, f2);
			}
			
			count = count.add(TWO);
		}
		
	}

}
