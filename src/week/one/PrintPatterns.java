package week.one;

public class PrintPatterns {
	// Total numbers of dots, stars or spaces
	private static final int COUNT = 15;
	// Strings of 15 dots, stars and spaces
    private static final String DOTS   = "...............";
    private static final String STARS  = "***************";
    private static final String SPACES = "               ";
    
	public static void main(String[] args) {
		printPattern1();
		printPattern2();
		printPattern3();
		printPattern4();
	}
	
	private static void printPattern1() {
		System.out.println("1)");
		for(int i = 1; i <= 4; i++) {
			System.out.println( getStars(i) );
		}
		System.out.println( getDots(9) );
		System.out.println();
	}
	
	private static void printPattern2() {
		System.out.println("2)");
		System.out.println( getDots(10) );
		for(int i = 4; i >= 1; i--) {
			System.out.println( getStars(i) );
		}
		System.out.println();
	}
	
	private static void printPattern3() {
		System.out.println("3)");
		for(int i = 5, j = 1; i >= 2; i--, j+=2) {
			System.out.print( getSpaces(i) );
			System.out.println( getStars(j) );
		}
		System.out.println( getDots(11) );
		System.out.println();
	}
	
	private static void printPattern4() {
		System.out.println("4)");
		System.out.println( getDots(12) );
		for(int i = 2, j = 7; i <= 5; i++, j-=2) {
			System.out.print( getSpaces(i) );
			System.out.println( getStars(j) );
		}
		System.out.println();
	}
	
	private static String getDots(int count) {
		count = (count < 0) ? 0 : count;
		if (count > COUNT) {
			return DOTS;
		}
		return DOTS.substring(0, count);
	}
	
	private static String getStars(int count) {
		count = (count < 0) ? 0 : count;
		if (count > COUNT) {
			return STARS;
		}
		return STARS.substring(0, count);
	}
	
	private static String getSpaces(int count) {
		count = (count < 0) ? 0 : count;
		if (count > COUNT) {
			return SPACES;
		}
		return SPACES.substring(0, count);
	}

}

