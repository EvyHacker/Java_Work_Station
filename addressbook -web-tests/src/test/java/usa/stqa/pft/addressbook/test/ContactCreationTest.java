package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

	@Test
	public void testCreationContact() {
		List<ContactData> before = app.contact().list();
		//int before = app.contact().getContactCount();
		ContactData contact = new ContactData().withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
				"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]");
		app.contact().create(contact,true);
//		int after = app.contact().getContactCount();
//		Assert.assertEquals(after, before +1);
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size() +1);

		before.add(contact);
		Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);

	}
}
