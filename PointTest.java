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
	
	@Test
	public void testCompare() {
	     Point p = new Point(111, 308);
	     Point q = new Point(226, 103);
	     Point r = new Point(336, 475);
	     double pqr = p.SLOPE_ORDER.compare(q,r);
	     assertTrue( p.SLOPE_ORDER.compare(q,r)==-1);
	     double a = p.slopeTo(q);
	     assertTrue( a == -1.7826086956521738);
	     double b = p.slopeTo(r);
	     assertTrue( b == 0.7422222222222222);
	}

	@Test
	public void testCompareB() {
	     Point p = new Point(26441, 16877);
	     Point q = new Point(10095, 13094);
	     Point r = new Point(26541, 30052);
	     double pqr = p.SLOPE_ORDER.compare(q,r);
	     assertTrue( pqr==-1);
	     double a = p.slopeTo(q);
	     assertTrue( a == 0.23143276642603694);
	     double b = p.slopeTo(r);
	     assertTrue( b == 131.75);
	}

	@Test
	public void testCompareC() {
	     Point p = new Point(6,4);
	     Point q = new Point(1,5);
	     Point r = new Point(7,8);
	     assertTrue( p.SLOPE_ORDER.compare(q,r)==-1);
	     assertTrue( p.slopeTo(q) == -0.2);
	     assertTrue( p.slopeTo(r) == 4);
	}
	
	@Test
	public void testCompareNull() {
		// Hack, these should be throwing exceptions
	}
	
	@Test
	public void testHorizontalPoints() {
		Point p = new Point(0,0);
		Point q = new Point(1,0);
		Point r = new Point(0,1);
		
		assertTrue( +0.0 == p.compareTo(q));
		assertTrue( +0.0 == q.compareTo(p));
		
		assertTrue( p.SLOPE_ORDER.compare(p, q) == 0 );
	}
	
	@Test
	public void testDegeneratePoints() {
		Point p = new Point(1,1);
		Point q = new Point(1,1);
		
		assertTrue( p.equals(q));
		assertTrue( p.compareTo(p) == Double.NEGATIVE_INFINITY);
		assertTrue( p.compareTo(q) == Double.NEGATIVE_INFINITY);
		
		assertTrue( p.SLOPE_ORDER.compare(p,q) == 0 );
	}
	
	@Test
	public void testVerticalLines() {
		Point p = new Point(0,0);
		Point q = new Point(1,0);
		Point r = new Point(0,1);
		
		assertTrue( Double.POSITIVE_INFINITY == p.compareTo(r));
		assertTrue( Double.POSITIVE_INFINITY == r.compareTo(p));
		
		assertTrue( p.SLOPE_ORDER.compare(p, r) == 0 );
		
	}
}
