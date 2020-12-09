package week.one;

public class AddValuesCmdLine {

	public static void main(String[] args) {
		long accumulator = 0L;
		System.out.println("You provided " + args.length + " arguments on the command line.");
		// Add all numbers provided on the command line
		// also handle the exception of invalid inputs
		for(String s : args) {
			try {
				accumulator += Long.parseLong(s);
			}
			catch (NumberFormatException nfe) {
				System.out.println("'" + s + "'" + " is not a number.");
			}
		}
		System.out.println("\nTotal: " + accumulator);
	}

}
