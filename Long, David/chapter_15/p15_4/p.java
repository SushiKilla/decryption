package p15_4;

import java.util.Scanner;

public class p {

	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		boolean done = false;
		
		BankAccount b = new BankAccount(10000, 10);
		
		System.out.println("Please enter amount to withdraw: you have " + b.getBalance());
		while(!done)
		{
			try {
				double amount = k.nextDouble();
				b.withdraw(amount);
				
				done = true;
				System.out.println("You withdrew " + amount);
			}
			catch(InsufficientFundsException e)
			{
				System.out.println("Not enough money. Enter a different amount");
			}
		}

		
		k.close();
	}

}
