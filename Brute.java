import java.util.Arrays;

public class Brute {
	
	public static void main(String[] args) {
        // read in the input
		In in = null;
		if ( args.length > 0 ) {
			String filename = args[0];
			in = new In(filename);
	        int N = in.readInt();
	        Point[] points = new Point[N];
	        for (int i = 0; i < N; i++) {
	            int x = in.readInt();
	            int y = in.readInt();
	            points[i] = new Point(x, y);
	        }
	        StdDraw.setXscale(0, 32768);
	        StdDraw.setYscale(0, 32768);
	        StdDraw.show(0);
	        StdDraw.setPenRadius(0.01);  // make the points a bit larger
	        
	        Arrays.sort(points);
	        for ( int x = 0; x < N - 3; x++ ) {
	        	for ( int y = x+1; y < N - 2; y++ ) {
	        		double s1 = points[x].slopeTo(points[y]);
	        		for ( int z = y+1; z < N -1; ++z ) {
	        			double s2 = points[y].slopeTo(points[z]);
	        			if ( s1 == s2 ) {
	        				for ( int w = z + 1; w < N; w++ ) {
	        					double s3 = points[z].slopeTo( points[w] );
	        					if ( s3 == s2 ) {
	        						StdOut.printf("%s -> %s -> %s -> %s\n", points[x].toString(), points[y].toString(), points[z].toString(), points[w].toString());
	        						points[x].drawTo(points[w]);
	        				        StdDraw.show(0);
	        					}
	        				}
	        			}
	        		}
	        	}
	        }
		}
	}
};
