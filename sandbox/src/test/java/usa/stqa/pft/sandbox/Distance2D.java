package usa.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Distance2D {

	
	@Test
	public void testPoint() {
		Distance p = new Distance(0.6, 0.5, 0.2, 0.5);

		//Assert.assertEquals(p.distance(), 25);
		Assert.assertEquals(p.distance(), 0.316227);
		System.out.println("Expected results " + p.distance());
	}

}
