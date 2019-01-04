package usa.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Point { 
    private double x;   
    private double y;   
    public static double dx;
    public static double dy;
   
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
@Test
    public static void main(String[] args) {
        Point p1 = new Point(0.6, 0.2);
        System.out.println("p1  = " + p1);
        Point p2 = new Point(0.5, 0.5);
        System.out.println("p2  = " + p2);
        System.out.println("dist(p, q) = " + p1.distance(p2));
        Assert.assertEquals(p1.distance(p2), 0.31622776601683794);
		System.out.println("Expected results " + p1.distance(p2));
    }
    
//    @Test
//	public void testPoint() {
//		distance p = new distance(p1, p2);
//
//		//Assert.assertEquals(p.distance(), 25);
//		Assert.assertEquals(p.distance(), 0.31622776601683794);
//		System.out.println("Expected results " + p1.distance(p2));
}