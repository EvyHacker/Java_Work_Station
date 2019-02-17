package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{


	@Test
	public void testContactDeletion() {
		app.getNavigationHelper().goToContactPage();
		if (! app.getContactHelper().isThereAContact()) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.getContactHelper().createContact(new ContactData("Evy", "Klimovich",
							"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
					true);
		}
		//int before = app.getContactHelper().getContactCount();
		List<ContactData> before = app.getContactHelper().getContactList();
		app.getContactHelper().selectContact();
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().messageCompleteDeletionContacts();
		app.getNavigationHelper().goToContactPage();
//		int after = app.getContactHelper().getContactCount();
//		Assert.assertEquals(after, before -1);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), before.size() -1);

		before.remove(before.size() - 1);
		//for (int i = 0; i< after.size(); i++){
		//  Assert.assertEquals(before.get(i), after.get(i));
		Assert.assertEquals(before, after);
	}

}