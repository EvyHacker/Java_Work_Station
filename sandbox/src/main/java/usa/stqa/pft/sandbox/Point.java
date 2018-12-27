package usa.stqa.pft.sandbox;


public class Point {

	public double x;
	public double y;
	
	public Point(double x, double y) { 
	this.x = x;
	this.y = y;
	}
 public static double distance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}
 
 public String toString() {
     return "(" + x + ", " + y + ")";
 }
 
	public static void main(String[] args) {
		Point p1 = new Point(4, 4);
		Point p2 = new Point(6,6 );
		System.out.println("Distance between two points = " + distance(p1, p2));

	}

}