package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase{

	@BeforeMethod
	public void ensurePreconditions(){
		app.goTo().contactPage();
		if (app.contact().all().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.contact().create(new ContactData().withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
							"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]"),
					true);
		}
	}
	@Test
	public void testContactDeletion() {
		Contacts before = app.contact().all();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		Contacts after = app.contact().all();
		assertThat(after.size(), equalTo(before.size() - 1));
		assertThat(after, equalTo(before.withoutAdded(deletedContact)));
	}
}