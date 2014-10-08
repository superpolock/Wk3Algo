import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PointTest {
	Point p;
	Point p1;
	Point p2;

	@Before
	public void setUp() throws Exception {
		p = new Point(1,2);
		p1 = new Point(1,0);
		p2 = new Point(0,1);
	}

	@Test
	public void testPoint() {
		Point p3 = new Point(3,0);
		Point p4 = new Point(3,0);
		assertTrue( null != p3 );
		assertTrue( p3.compareTo( p4 ) == 0 );
	}

	@Test
	public void testSlopeTo() {
		assertTrue( -1 == p1.slopeTo(p2));
		assertTrue( 1 == p1.slopeTo(new Point(2,1)));
		assertTrue( 0 == p1.slopeTo(new Point(2,0)));
	}
	
	@Test
	public void testHorizontalSlope() {
		assertTrue( Double.POSITIVE_INFINITY == p.slopeTo(p1));
		assertTrue( Double.POSITIVE_INFINITY == p1.slopeTo(p));
	}
	
	@Test
	public void testVerticalSlope() {
		assertTrue( Double.NEGATIVE_INFINITY == p.slopeTo(p));
	}

	@Test
	public void testCompareTo() {
		Point p3 = new Point(1,0);
		assertTrue( p1.compareTo(p3) == 0 );
		assertFalse( p1.compareTo(p2) > 0 );
	}

}
