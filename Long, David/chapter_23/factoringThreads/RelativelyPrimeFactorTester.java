package factoringThreads;

import java.math.BigInteger;
import java.util.Random;

public class RelativelyPrimeFactorTester {

	public static void main(String[] args) {
		Random r = new Random();
		BigInteger primeOne = BigInteger.probablePrime(32, r);
		System.out.println("Factor 1: " + primeOne);
		
		BigInteger primeTwo = BigInteger.probablePrime(32, r);	
		System.out.println("Factor 2: " + primeTwo);
		
		BigInteger relativelyPrime = primeOne.multiply(primeTwo);
		System.out.println("Number to be factored: " + relativelyPrime);
		
		int workers = 3;
		RelativelyPrimeFactorMaster m = new RelativelyPrimeFactorMaster(relativelyPrime, workers);
		System.out.println("Beginning to factor with " + workers + " workers.");
		m.factor();
		
	}

}
