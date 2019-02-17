package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase{


	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().goToContactPage();
		if (! app.getContactHelper().isThereAContact()) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.getContactHelper().createContact(new ContactData("Evy", "Klimovich",
							"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
					true);
		}
		int before = app.getContactHelper().getContactCount();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().messageCompleteDeletionContacts();
		app.getNavigationHelper().goToContactPage();
		int after = app.getContactHelper().getContactCount();
		Assert.assertEquals(after, before -1);

	}

}