/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
	private static class PointComparer implements Comparator<Point> {
    	public int compare(Point p1, Point p2 ) {
    		int yCompare = p2.y - p1.y;
    		if ( 0 == yCompare )
    			return p2.x - p1.x;
    		return yCompare;
    	}
    	boolean equals( Point p1, Point p2 ) {
    		return (p1.y == p2.y && p1.x == p2.x);
    	}
	};
	
    public final Comparator<Point> SLOPE_ORDER = new PointComparer(); 

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	if ( this.x != that.x )
    		return ( (double)( that.y - this.y )/(that.x - this.x));
    	else
    		return Double.NEGATIVE_INFINITY;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
		int yCompare = this.y - that.y;
		if ( 0 == yCompare )
			return this.x - that.x;
		return yCompare;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
