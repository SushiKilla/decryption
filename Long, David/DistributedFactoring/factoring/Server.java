package factoring;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerThread serverThread = new ServerThread(8887);
		serverThread.start();
		
		Scanner k = new Scanner(System.in);
		System.out.println("Enter text to start a computation");
		String line = k.nextLine();
		
		if (line!= null)
		{
		Random r = new Random();
		BigInteger primeOne = BigInteger.probablePrime(32, r);
		System.out.println("Factor 1: " + primeOne);
		BigInteger primeTwo = BigInteger.probablePrime(32, r);	
		System.out.println("Factor 2: " + primeTwo);	
		BigInteger relativelyPrime = primeOne.multiply(primeTwo);
		System.out.println("Number to be factored: " + relativelyPrime);
		
		serverThread.assignWork(relativelyPrime);
		}
	}
}
