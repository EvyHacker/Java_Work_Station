package usa.stqa.pft.sandbox;

import org.testng.annotations.Test;

public class Distance2D {

	
	@Test
	public void testPoin() {
		Distance d = new Distance (4,6,4,6);
		assert d.Point == 2.82;
			
		
		
	}
	
}
