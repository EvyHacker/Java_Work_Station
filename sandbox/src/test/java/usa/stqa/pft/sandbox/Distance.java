package usa.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Distance {

	@Test
	public void test1 (){
		Point p1 = new Point(0.6, 0.2);
		Point p2 = new Point(0.5, 0.5);
		System.out.println("dist(p1, p2) = " + p1.distance(p2));
		Assert.assertEquals(p1.distance(p2), 0.31622776601683794);
	}
	@Test
	public void testZero (){
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 0);
		System.out.println("dist(p1, p2) = " + p1.distance(p2));
		Assert.assertEquals(p1.distance(p2), 0.0);
	}
	@Test
	public void testY (){
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 3.0);
		System.out.println("dist(p1, p2) = " + p1.distance(p2));
		Assert.assertEquals(p1.distance(p2), 3.0);
	}
	@Test
	public void testX (){
		Point p1 = new Point(0, 0);
		Point p2 = new Point(99.99, 0);
		System.out.println("dist(p1, p2) = " + p1.distance(p2));
		Assert.assertEquals(p1.distance(p2), 99.99);
	}

}
