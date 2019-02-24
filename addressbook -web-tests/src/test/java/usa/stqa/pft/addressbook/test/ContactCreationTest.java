package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

	@Test
	public void testCreationContact() {
		Contacts before = app.contact().all();
		File photo = new File("src/test/resources/me.jpg");
		ContactData contact = new ContactData().withFirstName("Ievgeniia").withLastName("Gaidarenko").withPhoneNumber(
				"571-241-6524").withEmailAddress("gaidarenko1241@gmail.com").withPhoto(photo).withGroup("[none]");
		app.contact().create(contact,true);
		Contacts after = app.contact().all();
		assertThat(after.size(), equalTo(before.size() +1));
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
