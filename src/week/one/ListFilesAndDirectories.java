package week.one;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListFilesAndDirectories {

	private static final String SPACES = "          ";
	private static final int SUB_DIR_INDENT = 2;
	private static final Map<Integer, String> spacesMap = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Get the root directory from the user
		File dir = getExistingDirFromUser(sc);
		// FileAndLevel is a class that stores both File and sub-directory level.
		// sub-directory level is used for printing preceding spaces.
		// Use stack to traverse the directory in DFS approach.
		Deque<FileAndLevel> deq = new ArrayDeque<>();
		deq.push(new FileAndLevel(dir, 0));
		while(!deq.isEmpty()) {
			// Pop the top item off Deque, print it according to directory sub levels. 
			FileAndLevel fileLevel = deq.pop();
			File curFile = fileLevel.getFile();
			int level = fileLevel.getLevel();
			System.out.println(getSpaces(level * SUB_DIR_INDENT) + curFile.getName());
			// If the current file is a directory, push its children (file/directory) to deque with correct dir sub-level
			if (curFile.isDirectory()) {
				level++;
				File[] files = curFile.listFiles();
				for(int i = files.length-1; i >= 0; i--) {
					deq.push(new FileAndLevel(files[i], level));
				}
			}
		}
		sc.close();
	}
	
	/**
	 * Ask the user to enter an existing directory path.  The path can be either relative or absolute.
	 * DO NOT CLOSE Scanner in this method.
	 * 
	 * @param sc Scanner connected to System.in or user input
	 * @return File object of an existing directory
	 */
	private static File getExistingDirFromUser(Scanner sc) {
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
			if (!file.isDirectory()) {
				System.out.println("\"" + file.getAbsolutePath() + "\" is not a directory.");
			}
			System.out.println();
		} while(file == null || !file.isDirectory());
		return file;
	}
	
	/**
	 * Return a String of specified number of spaces.
	 * 
	 * @param n 
	 * @return String containing n numbers of spaces
	 */
	private static String getSpaces(int n) {
		if(!spacesMap.containsKey(n)) {
			StringBuilder sb = new StringBuilder();
			int l = SPACES.length();
			for(int i = 0; i < n/l; i++) {
				sb.append(SPACES);
			}
			sb.append(SPACES.substring(0, n%l));
			spacesMap.put(n, sb.toString());
		}
		return spacesMap.get(n);
	}
	
	private static class FileAndLevel {
		private final File file;
		private final int level;
		
		public FileAndLevel(File file, int level) {
			this.file = file;
			this.level = level;
		}

		public File getFile() {
			return file;
		}

		public int getLevel() {
			return level;
		}
	}

}


