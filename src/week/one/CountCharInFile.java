package week.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountCharInFile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Get file from user.
		File file = null;
		Path cwd = Paths.get(".").toAbsolutePath().getParent();
		// Keep asking the user for a file's path until s/he enters an existing readable file path
		do {
			System.out.println("Current working directory is \"" + cwd + "\"");
			System.out.print("Enter an existing file's path (relative/absolute): ");
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


		// Get a char from user.  It may be provided on the command line.  If not, ask for one.
		char ch;
		// A char was provided on the command line
		if (args.length > 0 && !args[0].isBlank()) {
			ch = args[0].trim().charAt(0);
		}
		// Ask the user for a character
		else {
			String str = null;
			do {
				System.out.print("Please enter a character to search for: ");
				str = sc.nextLine().trim();
			} while(str.isEmpty());
			ch = str.charAt(0);
		}
		sc.close();
		// Read the file char by char and count the frequency of character ch
		int count = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(file));) {
			int i;
			while((i = br.read()) != -1) {
				if (i == ch) {
					count++;
				}
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
		System.out.println("Count of " + ch + " in \"" + file.getName() + "\": " + count);
	}

}
