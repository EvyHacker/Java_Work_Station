package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

	@Test
	public void testCreationContact() {
		int before = app.getContactHelper().getContactCount();
		app.getContactHelper().createContact(new ContactData("Ievgeniia", "Gaidarenko",
						"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
				true);
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before +1);
	}

}
