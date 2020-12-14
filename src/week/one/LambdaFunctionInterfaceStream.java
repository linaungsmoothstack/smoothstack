package week.one;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaFunctionInterfaceStream {
	
	public static void main(String[] args) {
		String[] strArray = {"app", "bent", "ban", "call", "alpine", "Cat", "Banana", "Ape", "Apple", "all"};
		
		// Demonstrate lambda functionality.
		lambda(strArray);
		System.out.println();
		
		String resultStr = oddOrEven(40, 6, 11, 2, 53, 7);
		System.out.println(resultStr);
		
		List<String> resultList = startsWithCharALengthThree(Arrays.asList(strArray));
		System.out.println(resultList);
	}

	/**
	 * Demonstrate lambda functionality.
	 * @param strArray
	 */
	private static void lambda(String[] strArray) {
		//String[] strArray = {"app", "bent", "ban", "call", "Cat", "Banana", "Apple"};
		System.out.println("Original Array: " + formatArray(strArray));
		String[] sortedArray;
		
		// sort by length shortest to longest
		Comparator<String> byLengthShort = (s1, s2) -> s1.length()-s2.length();
		sortedArray = Arrays.stream(strArray)
				.sorted(byLengthShort)
				.toArray(String[]::new);
		System.out.println("Sorted by length shortest first: " + formatArray(sortedArray));
		
		// sort by length longest to shortest
		Comparator<String> byLengthLong = (s1, s2) -> s2.length()-s1.length();
		sortedArray = Arrays.stream(strArray)
				.sorted(byLengthLong)
				.toArray(String[]::new);
		System.out.println("Sorted by length longest first: " + formatArray(sortedArray));
		
		// sort by first char
		Comparator<String> byFirstChar = (s1, s2) -> s1.charAt(0) - s2.charAt(0);
		sortedArray = Arrays.stream(strArray)
				.sorted(byFirstChar)
				.toArray(String[]::new);
		System.out.println("Sorted by first char: " + formatArray(sortedArray));
		
		// sort by those that contains e first
		Comparator<String> byContainsE = (s1, s2) -> {
			// F, F ->  0
			// F, T ->  1
			// T, F -> -1
			// T, T ->  0
			boolean s1e = s1.contains("e");
			boolean s2e = s2.contains("e");
			if (s1e == s2e) return 0;
			if (s1e)        return -1;
			return 1;
		};
		sortedArray = Arrays.stream(strArray)
				.sorted(byContainsE)
				.toArray(String[]::new);
		System.out.println("Sorted by String that contains \"e\": " + formatArray(sortedArray));
		
		// sort by those that contains e first
		String[] copyArray = strArray.clone();
		Arrays.sort(copyArray, (s1, s2) -> {
			// F, F ->  0
			// F, T ->  1
			// T, F -> -1
			// T, T ->  0
			boolean s1e = s1.contains("e");
			boolean s2e = s2.contains("e");
			if (s1e == s2e) return 0;
			if (s1e)        return -1;
			return 1;
		});
		System.out.println("Sorted by String that contains \"e\" (Arrays.sort): " + formatArray(copyArray));
	}
	
	/**
	 * Returns a String of comma separated list of integers which were preceded by 'e' for even and 'o' for odd
	 * @param input a list of integers
	 * @return
	 */
	private static String oddOrEven(int...input) {
		return Arrays.stream(input)
				.mapToObj(i -> ((i%2 == 0)? "e" : "o") + i)
				.collect(Collectors.joining(","));
	}
	
	/**
	 * Returns a list of Stings that start with 'a' and has length of 3.
	 * @param list List<String>
	 * @return
	 */
	private static List<String> startsWithCharALengthThree(List<String> list) {
		return list.stream()
				.filter(s -> s.startsWith("a") && s.length()==3)
				.collect(Collectors.toList());
	}
	
	/**
	 * Return a format string of an array of object T.
	 * @param <T> Any object that has toString() overridden
	 * @param arr T[]
	 * @return String
	 */
	private static <T> String formatArray(T[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i].toString() + " ");
		}
		sb.append("]");
		return sb.toString();
	}

}
