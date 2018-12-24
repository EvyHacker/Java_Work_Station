package usa.stqa.pft.sandbox;

public class Point {

	static Distance d = new Distance(0.6, 0.5, 0.2, 0.5);

	public static void main(String[] args) {
		System.out.println("With this coordinates: " + " x1=" + d.x1 + " x2=" + d.x2 + " y1=" + d.y1 + " y2=" + d.y2);
		System.out.println("Distance between two points " + "=" + d.distance());

	}
}
