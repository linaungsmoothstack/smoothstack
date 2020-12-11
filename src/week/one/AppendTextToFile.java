package week.one;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AppendTextToFile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Get File object of an existing writable file from the user.
		File file = getExistingFileFromUser(sc);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			System.out.println("Please enter whatever you want to append to \"" + file.getAbsolutePath() + "\":");
			String str = sc.nextLine();
			bw.write(str);
			System.out.println("\"" + str + "\" appended to \"" + file.getAbsolutePath() + "\"");
		}
		catch (IOException ioe) {
			System.err.println("Unable to write to file.");
			ioe.printStackTrace();
		}
		sc.close();
	}
	
	/**
	 * Ask the user to enter an existing writable file's path.  The path can be either relative or absolute.
	 * DO NOT CLOSE Scanner in this method.
	 * 
	 * @param sc Scanner connected to System.in or user input
	 * @return File object of an existing writable file
	 */
	private static File getExistingFileFromUser(Scanner sc) {
		File file = null;
		Path cwd = Paths.get(".").toAbsolutePath().getParent();
		// Keep asking the user for a file's path until s/he enters an existing writable file path
		do {
			System.out.println("Current working directory is \"" + cwd + "\"");
			System.out.print("Enter an existing file's path (relative/absolute): ");
			// file representing the user's input path
			file = Paths.get(sc.nextLine()).toAbsolutePath().toFile();
			if (!file.exists()) {
				System.out.println("\"" + file.getAbsolutePath() + "\" does not exist.");
			}
			if (!file.isFile() || !file.canWrite()) {
				System.out.println("\"" + file.getAbsolutePath() + "\" is not a writable file.");
			}
			System.out.println();
		} while(file == null || !file.isFile() || !file.canWrite());
		return file;
	}

}
