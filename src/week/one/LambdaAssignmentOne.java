package week.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class LambdaAssignmentOne {

	public static void main(String[] args) {
		// Create Map of function index number to test function
		Map<Integer, Predicate<Integer>> predicateFnMap = new HashMap<Integer, Predicate<Integer>>();
		predicateFnMap.put(1, isOdd());
		predicateFnMap.put(2, isPrime());
		predicateFnMap.put(3, isPalindrome());
		
		// Get input file from the user
		Scanner scStdin = new Scanner(System.in);
		File inputFile = getInputFileFromUser(scStdin);
		
		try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			int numOfLines = Integer.parseInt(br.readLine());
			for (int i = 0; i < numOfLines; i++) {
				// number of inputs
				String[] inputs = br.readLine().split(" ");
				// function id and number to test
				Integer funcId = Integer.valueOf(inputs[0]);
				Integer testNum = Integer.valueOf(inputs[1]);
				String result;
				Predicate<Integer> func = predicateFnMap.get(funcId);
				switch(funcId) {
				case 1:
					result = (func.test(testNum)) ? "ODD" : "EVEN";
					break;
				case 2:
					result = (func.test(testNum)) ? "PRIME" : "COMPOSITE";
					break;
				case 3:
					result = (func.test(testNum)) ? "PALINDROME" : "NOT PALINDROME";
					break;
				default:
					result = "UNSUPPORTED TEST";
				}
				System.out.println(result);
			}
		}
		catch (FileNotFoundException fnfe) {
			System.err.println("Unable to find file.");
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			System.err.println("Unable to read file.");
			ioe.printStackTrace();
		}
		scStdin.close();
	}
	
	/**
	 * Return a predicate that tests whether an Integer is odd.
	 * @return boolean
	 */
	private static Predicate<Integer> isOdd() {
		return (i) -> i%2 == 1; 
	}
	
	/**
	 * Return a predicate that tests whether an Integer is prime.
	 * @return boolean
	 */
	private static Predicate<Integer> isPrime() {
		return (n) -> {
			// primer number starts at 2
	        if (n < 2) return false;
	        // check whether it is divisible by numbers from 2 to n-1
	        for (int i = 2; i < n; i++) {
	        	if (n%i == 0) return false;
	        }
	        return true; 
		};
	}
	
	/**
	 * Return a predicate that tests whether an Integer is a palindrome
	 * @return boolean
	 */
	private static Predicate<Integer> isPalindrome() {
		return (n) -> {
			String strNum = n.toString();
			for(int i = 0, j = strNum.length()-1; i < j; i++, j--) {
				if(strNum.charAt(i) != strNum.charAt(j)) {
					return false;
				}
			}
			return true;
		};
	}
	
	/**
	 * Ask the user to enter an existing input file path.  The path can be either relative or absolute.
	 * DO NOT CLOSE Scanner in this method.
	 * 
	 * @param sc Scanner connected to System.in or user input
	 * @return File object of an existing input file
	 */
	private static File getInputFileFromUser(Scanner sc) {
		File file = null;
		Path cwd = Paths.get(".").toAbsolutePath().getParent();
		// Keep asking the user for a file's path until s/he enters an existing file path
		do {
			System.out.println("Current working directory is \"" + cwd + "\"");
			System.out.print("Enter the input file's path (relative/absolute): ");
			// file representing the user's input path
			file = Paths.get(sc.nextLine()).toAbsolutePath().toFile();
			if (!file.exists()) {
				System.out.println("\"" + file.getAbsolutePath() + "\" does not exist.");
			}
			if (!file.isFile() || !file.canRead()) {
				System.out.println("\"" + file.getAbsolutePath() + "\" is not a readable file.");
			}
			System.out.println();
		} while(file == null || !file.isFile() || !file.canRead());
		return file;
	}

}
