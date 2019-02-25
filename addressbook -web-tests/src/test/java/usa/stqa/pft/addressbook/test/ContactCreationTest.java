package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;
import usa.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContacts(){
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] {new ContactData().withFirstName("Evy").withLastName("Klimovich")
				.withPhoneNumber("571-241-6524").withEmailAddress("evy@gmail.com")});
		list.add(new Object[] {new ContactData().withFirstName("Ievgeniia").withLastName("Gaidarenko")
				.withPhoneNumber("202-234-2323").withEmailAddress("evy@gsa.gov")});
		list.add(new Object[] {new ContactData().withFirstName("Maksim").withLastName("Pupkin")
				.withPhoneNumber("202-345-4545").withEmailAddress("maks@gmail.com")});
		return list.iterator();
	}

	@Test(dataProvider = "validContacts")
	public void testCreationContact(ContactData contact) {
		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/me.jpg");
		app.contact().create(contact,true);
		assertThat(app.contact().count(), equalTo(before.size() +1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.withAdded
				(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

	}

//	@Test
//	public void testCurrentDir(){
//		File currentDir = new File(".");
//		System.out.println(currentDir.getAbsolutePath());
//		File photo = new File("src/test/resources/me.jpg");
//		System.out.println(photo.getAbsolutePath());
//		System.out.println(photo.exists());
//	}

	@Test(enabled = false)
	public void testBadCreationContact() {
		Contacts before = app.contact().all();
		ContactData contact = new ContactData().withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
				"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withGroup("[none]");
		app.contact().create(contact,true);
		assertThat(app.contact().count(), equalTo(before.size() +1));
		Contacts after = app.contact().all();
		assertThat(after, equalTo(before.withAdded
				(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));;

	}
}
