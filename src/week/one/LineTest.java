package week.one;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LineTest {
	
	@Test
	public void getSlopeTest() {
		Line line1 = new Line(1.0, 1.0, 5.0, 5.0);
		Line line2 = new Line(0.0, 0.0, 1.0, -3.0);
		assertEquals(line1.getSlope(), 1.0, 0.0001);
		assertEquals(line2.getSlope(), -3.0, 0.0001);
	}
	
	@Test
	public void getDistanceTest() {
		Line line1 = new Line(1.0, 1.0, 4.0, 5.0);
		Line line2 = new Line(0.0, 0.0, 1.0, -3.0);
		assertEquals(line1.getDistance(), 5.0, 0.0001);
		assertEquals(line2.getDistance(), 3.16227766, 0.0001);
	}
	
	@Test
	public void parallelToTest() {
		Line line1 = new Line(1.0, 1.0, 5.0, 5.0);
		Line line2 = new Line(0.0, 0.0, 1.0, -3.0);
		Line line3 = new Line(-2.0, 2.0, -1.0, -1.0);
		assertEquals(line1.parallelTo(line1), true);
		assertEquals(line1.parallelTo(line2), false);
		assertEquals(line2.parallelTo(line3), true);
	}
}
