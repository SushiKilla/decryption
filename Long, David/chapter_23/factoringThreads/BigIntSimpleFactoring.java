package factoringThreads;

import java.math.BigInteger;
import java.util.ArrayList;

public class BigIntSimpleFactoring {
	
	public static ArrayList<BigInteger> getFactors(BigInteger n)
	{
		ArrayList<BigInteger> factors = new ArrayList<BigInteger>();

		BigInteger two = new BigInteger("2");
		
		while (n.mod(two).equals(BigInteger.ZERO))
		{
			factors.add(two);
			n = n.divide(two);
		}

		BigInteger sqrt = BigIntSqRoot.bigIntSqRootCeil(n).add(BigInteger.ONE);
		BigInteger count = new BigInteger("3");

		while (count.compareTo(sqrt) < 0)
		{
			if (count.compareTo(n) > 0) break;
			while (n.mod(count).equals(BigInteger.ZERO))
			{
				factors.add(count);
				n = n.divide(count);
			}
			count = count.nextProbablePrime();
		}
		if (!n.equals(BigInteger.ONE))
			factors.add(n);
		
		return factors;
	}
}
