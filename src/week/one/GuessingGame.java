package week.one;

import java.util.Scanner;

public class GuessingGame {
	private static final int START_NUM = 1;
	private static final int END_NUM = 100;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// A random number [1, 100]
		int randomNumber = (int)(Math.random() * (END_NUM - START_NUM + 1) + START_NUM);
		// Initial guess number is out of the range of tolerance(10)
		int guessedNumber = START_NUM - 11;
		for(int i = 0; i < 5; i++) {
			System.out.print("Guess a number between 1 and 100: ");
			try {
				// Read the user's input from stdin
				guessedNumber = sc.nextInt();
				// If the users' guess is within +/- 10 of the random number
				if ( Math.abs( randomNumber - guessedNumber ) <= 10 ) {
					break;
				} else {
					System.out.println("Bad guess.  Try again.");
				}
			} catch (Exception e) {
				System.out.println("Invalid input.  Guess a number between 1 and 100.  Try again.");
				sc.nextLine();
			} finally {
				System.out.println();
			}
		}
		if ( Math.abs( randomNumber - guessedNumber ) > 10 ) {
			System.out.print("Sorry.  ");
		}
		System.out.println("The random number is: " + randomNumber);
	}

}
