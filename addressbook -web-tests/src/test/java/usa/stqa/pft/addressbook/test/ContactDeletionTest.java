package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase{

	@BeforeMethod
	public void ensurePreconditions(){
		if (app.db().contacts().size() == 0){
		app.goTo().goToContactPage();
			app.contact().create(new ContactData().withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
							"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]"),
					true);
		}
	}
	@Test
	public void testContactDeletion() {
		Contacts before = app.db().contacts();
		ContactData deletedContact = before.iterator().next();
		app.contact().delete(deletedContact);
		assertThat(app.contact().count(), equalTo(before.size() -1));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.withoutAdded(deletedContact)));
	}
}