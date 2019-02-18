package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import usa.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

	static int Message;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void submitContactCreation() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void createContact(ContactData contactData, boolean creation) {
		addNewContact();
		fillFormContact(contactData, creation);
		submitContactCreation();
		goToContactPage();

	}
	public void modifyContact(int index, ContactData contact) {
		selectContact(index);
		initContactModification(index);
		fillFormContact(contact, false);
		submitContactModification();
		goToContactPage();
	}

	private void goToContactPage() {
		wd.findElement(By.linkText("home page")).click();
	}

	public void fillFormContact(ContactData parameterObject, boolean creation) {
		type(By.name("firstname"), parameterObject.getFirstName());
		type(By.name("lastname"), parameterObject.getLastName());
		type(By.name("mobile"), parameterObject.getPhoneNumber());
		type(By.name("email"), parameterObject.getEmailAddress());

		if (creation) {
			new Select(wd.findElement(By.name("new_group")))
					.selectByVisibleText(parameterObject.getGroup());
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

	public void selectContact(int index) {
		wd.findElements(By.name("selected[]")).get(index).click();


	}

	public void initContactModification(int index) {
		click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));

	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}

	public List<ContactData> getContactList() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		List<WebElement> elements = wd.findElements(By.name("entry"));
		for (WebElement element : elements) {
			int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			String LastName = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[2]")).getText();
			String FirstName = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[3]")).getText();
			ContactData contact = new ContactData(id, FirstName, LastName);
			contacts.add(contact);
		}

		return contacts;
	}

}
