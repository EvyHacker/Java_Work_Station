package usa.stqa.pft.addressbook.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;
import usa.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTest extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContacts() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))){
			String xml = "";
			String line = reader.readLine();
			while (line != null){
				xml += line;
				line = reader.readLine();
			}
			XStream xstream = new XStream();
			xstream.processAnnotations(ContactData.class);
			List <ContactData> contacts =  (List <ContactData>)xstream.fromXML(xml);
			return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
			}
		}

	@DataProvider
	public Iterator<Object[]> validContactsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))){
			String json = "";
			String line = reader.readLine();
			while (line != null){
				json += line;
				line = reader.readLine();
			}
			Gson gson =new Gson();
			List <ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());//<List<GroupData>.class
			return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
			}
		}

	@Test(dataProvider = "validContactsFromJson")
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
