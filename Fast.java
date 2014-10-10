import java.util.Arrays;
import java.util.Comparator;

public class Fast {
	private class IdxSlope implements Comparable<IdxSlope>  {
		int idx; // idx in points array
		double slope;
		public int compareTo(IdxSlope other) {
			if ( slope == other.slope ) {
				return idx - other.idx;
			}
			else if ( slope < other.slope )
				return -1;
			else
				return 1;
		}
	};
	
	private void InitializeDraw() {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
	}
	
    private int N;
    private Point[] points;
    
	private void readPoints(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        N = in.readInt();
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
	}
	
    public int[][] colPoints;
    public IdxSlope[] slopes;
    
	private void initializeData() {
		this.colPoints=new int[N][N];
		this.slopes=new IdxSlope[N];
        // Create an array to store the slopes and point index for the current points being examined
        for( int n = 0; n < N; ++n ) {
        	this.slopes[n] = new IdxSlope();
        }
        // Create an NxN array to store the collinear points. Or N linked list pointing to the next point sharing the same slope. Each point with a slope that matches another slope
	}
	
	private void drawPoints() {
		for( Point x : points) {
            x.draw();
	        StdDraw.show(0);
		}
	}
	
	private void calculateSlopes(int x) {
    	for ( int z=0; z<=x; z++) {
    		slopes[z].idx = -1;
    		slopes[z].slope = Double.NEGATIVE_INFINITY;
    	}
    	// x is the initial point we are determining slope from
    	for (int y=x+1; y<N; ++y) {
    		slopes[y].idx = y;
    		slopes[y].slope=points[x].slopeTo(points[y]);
//    		StdOut.printf("%d => %s -> %s\n ", y, points[x].toString(), points[y].toString());
    	}
    	Arrays.sort(slopes);
    	// Find similar slopes from points, and store in colPoints if we have enough in a row
    	for (int w=x+1; w < N; ++w ) {
    		final double slopeToMatch = slopes[w].slope;
    		final int initialIdx = w++;
    		StdOut.printf("Slope %f: %s", slopeToMatch, points[x].toString());
    		while ( (w < N-1) && (slopes[w+1].slope==slopeToMatch) ) {
				StdOut.printf(" -> %s", points[w].toString());
    			w++;
    		}
    		StdOut.printf("\n");
    		// w now points to the last point which matches the initial slope
    		final int pointsOnLine = 2 + w - initialIdx;
    		if ( pointsOnLine > 3 ) {
    			w = initialIdx;
        		while ( (w < N) && (slopes[w].slope==slopeToMatch) ) {
					StdOut.printf(" -> %s", points[w++].toString());
        		}
				points[ x ].drawTo( points[ slopes[ w ].idx ] );
		        StdDraw.show(0);
    		}
    	}
    	StdOut.printf("\n");
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // read in the input
        if ( args.length > 0 ) {
	        Fast fast = new Fast();
	        fast.InitializeDraw();
	        fast.readPoints( args );
	        fast.drawPoints();
			
	        fast.initializeData();

	        for (int x=0; x<fast.N-1; ++x) {
	        	fast.calculateSlopes(x);
	        	
	        	// for segments with four or more identical slopes, draw the lines
	        }
        }
	}

}
