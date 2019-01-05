package usa.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupDeletionClass extends TestBase{


	@Test
	public void testGroupDeletionContact() {
		app.goToContactPage();
		app.selectContact();
		//acceptNextAlert = true;
		app.deleteSelectedContact();
		//assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
		app.goToContactPage();
		
	}

}