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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // read in the input
        if ( args.length > 0 ) {
	        String filename = args[0];
	        In in = new In(filename);
	        int N = in.readInt();
	        Point[] points = new Point[N];
	        // rescale coordinates and turn on animation mode
	        StdDraw.setXscale(0, 32768);
	        StdDraw.setYscale(0, 32768);
	        StdDraw.show(0);
	        StdDraw.setPenRadius(0.01);  // make the points a bit larger
			
	        for (int i = 0; i < N; i++) {
	            int x = in.readInt();
	            int y = in.readInt();
	            points[i] = new Point(x, y);
	            points[i].draw();
		        StdDraw.show(0);
	        }
	        
	        Arrays.sort(points);
	        // Create an array to store the slopes and point index for the current points being examined
	        IdxSlope[] slopes=new IdxSlope[N];
	        Fast fast = new Fast();
	        for( int n = 0; n < N; ++n ) {
	        	slopes[n] = fast.new IdxSlope();
	        }
	        // Create an NxN array to store the collinear points. Or N linked list pointing to the next point sharing the same slope. Each point with a slope that matches another slope
	        int[][] colPoints=new int[N][N];

	        for (int x=0; x<N-1; ++x) {
	        	for ( int z=0; z<=x; z++) {
	        		slopes[z].idx = -1;
	        		slopes[z].slope = Double.NEGATIVE_INFINITY;
	        	}
	        	// x is the initial point we are determining slope from
	        	for (int y=x+1; y<N; ++y) {
	        		slopes[y].idx = y;
	        		slopes[y].slope=points[x].slopeTo(points[y]);
	        		StdOut.printf("%d => %s -> %s\n ", y, points[x].toString(), points[y].toString());
	        	}
	        	Arrays.sort(slopes);
	        	// Find similar slopes from points, and store in colPoints if we have enough in a row
	        	for (int w=x+1; w < N; ++w ) {
	        		final double slopeToMatch = slopes[w].slope;
	        		final int initialIdx = w++;
//	        		StdOut.printf("Slope %f: %s", slopeToMatch, points[w-1].toString());
	        		while ( (w < N) && (slopes[w].slope==slopeToMatch) ) {
	//					StdOut.printf(" -> %s", points[w].toString());
	        			w++;
	        		}
	  //      		StdOut.printf("\n");
	        		// w now points to the last point which matches the initial slope
	        		final int pointsOnLine = 2 + w - initialIdx;
	        		if ( pointsOnLine > 4 ) {
	        			// For each matching slope, record the index it is collinear with
	        			// HACK - how do we make sure we don't duplicate a subsection of a previously matched string?
	        			//  Answer? We have initialized all colPoints. When we find a collinear point, lets put an indicator in the colPoints array to say we can't populate the value
	        			// Does this resolve the problem of a point being collinear with multiple sets of points? For example. [0,0][1,0][2,0] and [0,0],[0,1],[0,2]
	        			for ( int slopeIdx=initialIdx; slopeIdx < w; ++slopeIdx ){
	        				StdOut.printf("Line: %s -> %s",  points[x].toString(), points[slopes[slopeIdx].idx]);
        					points[ x ].drawTo( points[ slopes[ slopeIdx ].idx ] );
        			        StdDraw.show(0);
	        				colPoints[slopeIdx][slopes[slopeIdx].idx] = slopeIdx;
	        			}
	        		}
	        	}
	    //    	StdOut.printf("\n");
	        }
        }
	}

}
