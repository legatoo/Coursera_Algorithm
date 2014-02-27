import java.util.ArrayList;
import java.util.Collections;

import auxpackage.Point;
import auxpackage.LStack;

public class ConvexHull {

//	public void findConvexHull(ArrayList<Point> points){
//		Collections.sort(points, new Point().BY_Y);
//	}
	/***
	 * Graham Scan Algorithm a stack holds the possible vertices of convex hull
	 * first find the point with lowest y coordinate, start with it sort all the
	 * point based on the pole angle with the start point pick one from the
	 * sorted points, decide weather this point and the top two points are in
	 * the counter clockwise order, if so, push this point to the stack
	 * otherwise, pop the top point of stack, and continually check the new top
	 * two and the current point. the algorithm ends when all the sorted points
	 * are checked
	 **/
	
	//TODO: finish graham scan algorithm

}