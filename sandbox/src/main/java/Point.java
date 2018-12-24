
public class Point {

	static Distance d = new Distance(4, 6, 4, 6);

	

	public static void main(String[] args) {
//		Point p1 = new Point();
//		Point p2 = new Point();
		System.out.println("With this coordinates: " +" x1=" + d.x1 +" x2=" + d.x2+" y1=" + d.y1+" y2=" + d.y2 );
		System.out.println("Distance between two points "  + "=" + d.distance());

	}
}
