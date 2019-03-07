package usa.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class ContactModificationTest extends TestBase {

	@BeforeMethod
			public void ensurePreconditions(){
		if (app.db().contacts().size() == 0){
			app.goTo().goToContactPage();
			app.contact().create(new ContactData().withFirstName("Evy").withLastName("Klimovich").withPhoneNumber(
							"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]"),
					true);
		}
	}
	@Test
	public void testContactModification() {
		Contacts before = app.db().contacts();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
				"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]");
		app.goTo().goToContactPage();
		app.contact().modify(contact);
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(before.withoutAdded(modifiedContact).withAdded(contact)));
	}


}