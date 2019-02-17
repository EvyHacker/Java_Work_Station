package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;

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
		app.getContactHelper().selectContact();
		app.getContactHelper().initContactModification();
		app.getContactHelper()
				.fillFormContact(new ContactData("Ievgeniia", "Gaidarenko",
						"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
						false);
		app.getContactHelper().submitContactModification();
		app.getNavigationHelper().goToContactPage();
//		int after = app.getContactHelper().getContactCount();
//		Assert.assertEquals(after, before);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() );
	}
}