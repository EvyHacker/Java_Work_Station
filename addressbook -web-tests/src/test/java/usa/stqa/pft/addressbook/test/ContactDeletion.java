package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;

public class ContactDeletion extends TestBase{


	@Test
	public void testGroupDeletionContact() throws InterruptedException {
		app.getNavigationContactHelper().goToContactPage();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getNavigationContactHelper().goToContactPage();

	}

}