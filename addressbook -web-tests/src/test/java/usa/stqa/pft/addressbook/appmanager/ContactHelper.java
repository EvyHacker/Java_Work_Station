package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

	static int Message;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void submitContactCreation() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void create(ContactData contactData, boolean creation) {
		addNewContact();
		fillFormContact(contactData, creation);
		submitContactCreation();
		contactCache = null;
		goToContactPage();

	}
	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModification();
		fillFormContact(contact, false);
		submitContactModification();
		contactCache = null;
		goToContactPage();
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteSelectedContact();
		messageCompleteDeletionContacts();
		contactCache = null;
		goToContactPage();
	}

	private void selectContactById(int id) {
		wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
	}

	private void goToContactPage() {
		wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
	}

	public void fillFormContact(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("mobile"), contactData.getPhoneNumber());
		type(By.name("email"), contactData.getEmailAddress());

		if (creation) {
			new Select(wd.findElement(By.name("new_group")))
					.selectByVisibleText(contactData.getGroup());
		} else {
			Assert.assertFalse(isElementPresent(By.name("new_group")));
		}
	}

	public void addNewContact() {
		click(By.linkText("add new"));
	}

	public void deleteSelectedContact() {
		click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
		wd.switchTo().alert().accept();

	}
	public void messageCompleteDeletionContacts() {
		wd.findElement(By.xpath("//*[contains(text(), 'Record successful deleted')]"));
	}

	public void initContactModification() {
		click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));

	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	private Contacts contactCache = null;

	public Contacts all() {
		if (contactCache != null){
			return new Contacts(contactCache);
		}
		contactCache = new Contacts();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {

			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			String lastName = element.findElement(By.xpath("td[2]")).getText();
			String firstName = element.findElement(By.xpath("td[3]")).getText();
			contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
		}

		return new Contacts(contactCache);
	}


}
