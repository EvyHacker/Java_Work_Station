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
			app.goTo().goToContactPage();
		if (app.contact().all().size() == 0) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTest
			app.contact().create(new ContactData().withFirstName("Evy").withLastName("Klimovich").withPhoneNumber(
							"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]"),
					true);
		}
	}
	@Test
	public void testContactModification() {
		Contacts before = app.contact().all();
		ContactData modifiedContact = before.iterator().next();
		ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
				"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]");
		app.contact().modify(contact);
		assertThat(app.contact().count(), equalTo(before.size()));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.withoutAdded(modifiedContact).withAdded(contact)));
	}


}