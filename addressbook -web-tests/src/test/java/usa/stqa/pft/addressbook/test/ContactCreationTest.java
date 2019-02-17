package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

	@Test
	public void testCreationContact() {
		app.getContactHelper().createContact(new ContactData("Ievgeniia", "Gaidarenko",
						"571-241-6524", "gaidarenko1241@gmail.com", "test1"),
				true);
	}

}
