package usa.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Distance {

	@Test
	public void test1 (){
		Point p1 = new Point(0.6, 0.2);
		System.out.println("p1  = " + p1);
		Point p2 = new Point(0.5, 0.5);
		System.out.println("p2  = " + p2);
		System.out.println("dist(p, q) = " + p1.distance(p2));
		Assert.assertEquals(p1.distance(p2), 0.31622776601683794);
	}


}
