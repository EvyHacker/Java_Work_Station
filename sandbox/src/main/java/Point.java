
public class Point {

	

	
//	public static double x2 = 0.5;
//	public static double y1 = 0.2;
//	public static double y2 = 0.5;

		
		 static Distance d = new Distance(4, 6, 4, 6);
		
		

		public static double distance(Point p1, Point p2) {
			return Math.sqrt((d.x2 - d.x1) * (d.x2 - d.x1) + (d.y2 - d.y1) * (d.y2 - d.y1));
		}

		public static void main(String[] args) {
			Point p1 = new Point();
			Point p2 = new Point();
			System.out.println("Distance between two points + " + distance(p1, p2));

		}
		}
