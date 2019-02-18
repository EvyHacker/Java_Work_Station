package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

	@BeforeMethod
	public void ensurePreconditions(){
		app.getNavigationHelper().goToContactPage();
		if (! app.getContactHelper().isThereAContact()) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.getContactHelper().createContact(new ContactData("Evy", "Klimovich",
							"571-241-6524", "gaidarenko1241@gmail.com", "[none]"),
					true);
		}
	}
	@Test
	public void testContactDeletion() {
		//int before = app.getContactHelper().getContactCount();
		List<ContactData> before = app.getContactHelper().getContactList();
		int index = before.size() - 1;
		app.getContactHelper().selectContact(index);
		app.getContactHelper().deleteSelectedContact();
		app.getContactHelper().messageCompleteDeletionContacts();
		app.getNavigationHelper().goToContactPage();
//		int after = app.getContactHelper().getContactCount();
//		Assert.assertEquals(after, before -1);
		List<ContactData> after = app.getContactHelper().getContactList();
		Assert.assertEquals(after.size(), index);

		before.remove(index);
		//for (int i = 0; i< after.size(); i++){
		//  Assert.assertEquals(before.get(i), after.get(i));
		Assert.assertEquals(before, after);
	}

}