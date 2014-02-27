package auxpackage;

import java.util.Comparator;

public class Point {
	public final Comparator<Point> BY_POLARANGLE = new ByPolarAngle();
	public final Comparator<Point> BY_Y = new BYY();
	private final int x;
	private final int y;

	public Point() {
		x = 0;
		y = 0;
	}

	public Point(int a, int b) {
		x = a;
		y = b;
	}

	private static int ccw(Point a, Point b, Point c) {
		int determinant = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (determinant < 0)
			return -1; // clockwise
		if (determinant > 0)
			return +1; // counter clockwise
		else
			return 0; // co-linear
	}

	private class ByPolarAngle implements Comparator<Point> {

		@Override
		public int compare(Point p1, Point p2) {
			// TODO Auto-generated method stub
			int dx1 = p1.x - x;
			int dy1 = p1.y - y;
			int dx2 = p2.x - x;
			int dy2 = p2.y - y;

			if (dy1 == 0 && dy2 == 0) {
				if ((dx1 == 0 && dx2 == 0) || (dx1 > 0 && dx2 > 0)
						|| (dx1 < 0 && dx2 < 0))
					return 0;
				else if (dx1 == 0)
					return -1;
				else if (dx2 == 0)
					return +1;
				else if (dx1 > 0)
					return -1;
				else
					return +1;
			} else if (dy1 >= 0 && dy2 < 0)
				return -1;
			else if (dy2 >= 0 && dy1 < 0)
				return +1;
			else
				return -ccw(Point.this, p1, p2);
		}
	}

	private class BYY implements Comparator<Point> {

		@Override
		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			return o1.y - o2.y;
		}

	}

}