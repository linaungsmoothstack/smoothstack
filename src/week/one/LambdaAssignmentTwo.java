package week.one;

import java.util.Arrays;

public class LambdaAssignmentTwo {

	public static void main(String[] args) {
		int[] inputArray = {1, 22, 93, 80, 24};
		int[] outputArray = Arrays.stream(inputArray).map(i -> i%10).toArray();
		System.out.println("Input:  " + formatArray(inputArray));
		System.out.println("Output: " + formatArray(outputArray));
	}
	
	private static String formatArray(int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i] + " ");
		}
		sb.append("]");
		return sb.toString();
	}
}