package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() {
		app.getNavigationHelper().goToContactPage();
		if (! app.getContactHelper().isThereAContact()) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.getContactHelper().createContact(new ContactData("Evy", "Klimovich",
							"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
					true);
		}
		//int before = app.getContactHelper().getContactCount();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact(before.size() - 1);
		app.getContactHelper().initContactModification(before.size() - 1);
		ContactData contact = new ContactData("Ievgeniia", "Gaidarenko",
				"571-241-6524", "gaidarenko1241@gmail.com", "[none]");
		app.getContactHelper().fillFormContact(contact, false);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().goToContactPage();
//		int after = app.getContactHelper().getContactCount();
//		Assert.assertEquals(after, before);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() );

		before.remove(before.size() - 1);
		before.add(contact);
		Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(), c2.getId());
		before.sort(byId);
		after.sort(byId);
		Assert.assertEquals(before, after);
	}
}