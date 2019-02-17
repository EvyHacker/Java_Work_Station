package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

	@Test
	public void testCreationContact() {
		List<ContactData> before = app.getContactHelper().getContactList();
		//int before = app.getContactHelper().getContactCount();
		app.getContactHelper().createContact(new ContactData("Ievgeniia", "Gaidarenko",
						"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
				true);
//		int after = app.getContactHelper().getContactCount();
//		Assert.assertEquals(after, before +1);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() +1);
	}

}
