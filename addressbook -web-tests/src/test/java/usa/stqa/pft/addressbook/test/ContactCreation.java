package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

public class ContactCreation extends TestBase {

	@Test
	public void testCreationContact() {
		app.getContactHelper().addNewContact();
		app.getContactHelper()
				.fillFormContact(new ContactData("Ievgeniia", "Gaidarenko",
						"571-241-6524", "gaidarenko1241@gmail.com", "test1"),
						true);
		app.getContactHelper().submitContactCreation();
		app.getNavigationContactHelper().goToContactPage();

	}
}
