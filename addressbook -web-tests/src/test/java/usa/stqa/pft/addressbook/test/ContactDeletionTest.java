package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase{

	@BeforeMethod
	public void ensurePreconditions(){
		app.goTo().contactPage();
		if (app.contact().list().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.contact().create(new ContactData().withFirstName("Evy").withLastName("Klimovich").withPhoneNumber(
							"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]"),
					true);
		}
	}
	@Test
	public void testContactDeletion() {
		//int before = app.contact().getContactCount();
		List<ContactData> before = app.contact().list();
		int index = before.size() - 1;
		app.contact().delete(index);
//		int after = app.contact().getContactCount();
//		Assert.assertEquals(after, before -1);
		List<ContactData> after = app.contact().list();
		Assert.assertEquals(after.size(), before.size() - 1);

		before.remove(index);
		//for (int i = 0; i< after.size(); i++){
		//  Assert.assertEquals(before.get(i), after.get(i));
		Assert.assertEquals(before, after);
	}



}