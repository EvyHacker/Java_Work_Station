package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import usa.stqa.pft.addressbook.model.GroupContactData;

public class ContactHelper {
	private ChromeDriver wd;

	public ContactHelper(ChromeDriver wd) {
		this.wd = wd;
	}

	public void submitContactCreation() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	private void click(By locator) {
		wd.findElement(locator).click();
	}

	public void fillFormContact(GroupContactData parameterObject) {
		type(By.name("firstname"), parameterObject.getFirstName());
		type(By.name("lastname"), parameterObject.getLastName());
		type(By.name("mobile"), parameterObject.getPhoneNumber());
		type(By.name("email"), parameterObject.getEmailAddress());
	}

	private void type(By locator, String text) {
		wd.findElement(locator).click();
		wd.findElement(locator).clear();
		wd.findElement(locator).sendKeys(text);
	}

	public void addNewContact() {
		click(By.linkText("add new"));
	}

	public void deleteSelectedContact() throws InterruptedException {
		click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
		click(By.name("update"));

	}

	public void selectContact() {
		click(By.name("selected[]"));
	}

}
