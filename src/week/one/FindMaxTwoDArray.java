package week.one;

import java.util.Random;

public class FindMaxTwoDArray {
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 10;
	private static final int MIN_RANDOM_NUM = 1;
	private static final int MAX_RANDOM_NUM = 1000;
	private static final Random rand = new Random();

	public static void main(String[] args) {
		int[][] twoDArray = new int[NUM_ROWS][NUM_COLS];
		// Populate 2D array with random integers
		for(int i = 0; i < twoDArray.length; i++) {
			for (int j = 0; j < twoDArray[i].length; j++) {
				twoDArray[i][j] = getNextRandInt();
			}
		}
		// Find the max value and its indexes
		int row = 0,
			col = 0,
			maxValue = MIN_RANDOM_NUM;
		for(int i = 0; i < twoDArray.length; i++) {
			for (int j = 0; j < twoDArray[i].length; j++) {
				if (twoDArray[i][j] > maxValue) {
					row = i;
					col = j;
					maxValue = twoDArray[i][j];
				}
			}
		}
		System.out.println("Max Value: Array[" + row + "][" + col + "]: " + maxValue);
	}
	
	private static int getNextRandInt() {
		return (int)(rand.nextInt(MAX_RANDOM_NUM - MIN_RANDOM_NUM + 1) + MIN_RANDOM_NUM);
	}

}
