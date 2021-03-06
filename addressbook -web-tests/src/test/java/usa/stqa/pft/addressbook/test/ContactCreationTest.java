package usa.stqa.pft.addressbook.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

	@DataProvider
	public Iterator<Object[]> validContactsFromCsv() throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
		String line = reader.readLine();
		while (line != null) {
			String[] split = line.split(";");
			list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastName(split[2])
					.withPhoneNumber(split[3]).withEmailAddress(split[4])});
			line = reader.readLine();
		}
		return list.iterator();
	}

	@DataProvider
	public Iterator<Object[]> validContactsFromXml() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
			String xml = "";
			String line = reader.readLine();
			while (line != null) {
				xml += line;
				line = reader.readLine();
			}
			XStream xstream = new XStream();
			xstream.processAnnotations(ContactData.class);
			List<ContactData> groups = (List<ContactData>) xstream.fromXML(xml);
			return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
		}
	}

	@DataProvider
	public Iterator<Object[]> validContactsFromJson() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
			String json = "";
			String line = reader.readLine();
			while (line != null) {
				json += line;
				line = reader.readLine();
			}
			Gson gson = new Gson();
			List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>() {
			}.getType()); // List<ContactData>.class
			return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
		}
	}

	@Test(dataProvider = "validContactsFromXml")
	public void testContactCreation(ContactData contact) throws Exception {
		Contacts before = app.db().contacts();
		app.contact().create(contact, true);
		assertThat(app.contact().count(), equalTo(before.size() + 1)); // Проверка на основе хеширования
		Contacts after = app.db().contacts();
		assertThat(after, equalTo(
				before.withAdded(contact.withId(after.stream().mapToInt(c -> c.getId()).max().getAsInt()))));
		verifyContactListInUi();
	}

	@Test(enabled = false) // Определен относительный пункт к файлу фотографии, добавлен тест на пр…
	public void testCurrentDir() {
		File currentDir = new File(".");
		System.out.println(currentDir.getAbsolutePath());
		File photo = new File("src/test/resources/me.jpg");
		System.out.println(photo.getAbsolutePath());
		System.out.println(photo.exists());
	}
}
