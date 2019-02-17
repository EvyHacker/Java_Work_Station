package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import usa.stqa.pft.addressbook.model.ContactData;

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

	public void selectContact() {
		click(By.name("selected[]"));

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

	public int getContactCount() {
		return wd.findElements(By.name("selected[]")).size();
	}
}