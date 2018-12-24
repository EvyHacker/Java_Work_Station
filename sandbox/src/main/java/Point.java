
public class Point {

		public static double x1 = 0.6;
		public static double x2 = 0.5;
		public static double y1 = 0.2;
		public static double y2 = 0.5;

		public static double distance(Point p1, Point p2) {
			return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
		}

		public static void main(String[] args) {
			Point p1 = new Point();
			Point p2 = new Point();
			System.out.println("Distance between two points + " + distance(p1, p2));

		}
	}