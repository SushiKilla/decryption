package factoringThreads;

import java.math.BigInteger;
import java.util.ArrayList;

public class FactoringTester {

	public static void main(String[] args) {
		long timeIn = System.currentTimeMillis();
		ArrayList<BigInteger> factors = BigIntSimpleFactoring.getFactors(new BigInteger("1234787123947123444444"));

		for (BigInteger b: factors)
			System.out.println(b.toString());
		
		System.out.println("Single Thread: ");
		System.out.println("Time Spent: " + (System.currentTimeMillis()-timeIn));
	}

}
