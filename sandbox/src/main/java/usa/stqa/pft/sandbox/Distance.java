package usa.stqa.pft.sandbox;

public class Distance {

	public static double x1;
	public static double x2;
	public static double y1;
	public static double y2;

	public Distance(double x1, double x2, double y1, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;

	}

	public double distance() {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
}
