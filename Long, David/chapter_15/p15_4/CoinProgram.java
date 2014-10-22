package p15_4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoinProgram {

	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		System.out.println("Enter a number of coins followed by its name (e.g. 5 quarters)");
		
		boolean d = false;
		while (!d)
		{
			try
			{
				int num = Integer.parseInt(k.next());
				if (num == -1)
				{
					d = true; break;
				}
				String coin = k.next();
				
				System.out.println("You received " + num + " " + coin);
				
				System.out.println();
				System.out.println("Enter a number of coins followed by its name (e.g. 5 quarters)");
				System.out.println("or enter '-1' to quit");
			}
			catch (InputMismatchException e)
			{
				System.out.println("Formatted incorrectly. Please enter it again");
			}
		}
		
		k.close();
		System.out.println("Finished");
	}

}
