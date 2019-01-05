package usa.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import usa.stqa.pft.addressbook.model.GroupContactData;
import usa.stqa.pft.addressbook.model.GroupData;

public class ApplicationManager {

	public static boolean isAlertPresent(ChromeDriver wd) {
		try {
			wd.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	WebDriver wd;

	public void init() {
		System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/edit.php");
		login("admin", "secret");
	}

	public void login(String username, String password) {
		wd.findElement(By.name("user")).clear();
		wd.findElement(By.name("user")).sendKeys(username);
		wd.findElement(By.name("pass")).clear();
		wd.findElement(By.name("pass")).sendKeys(password);
		wd.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"))
				.click();
	}

	public void returnToGroupPage() {
		wd.findElement(By.linkText("Logout")).click();
	}

	public void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}

	public void fillGroupForm(GroupData groupData) {
		wd.findElement(By.name("group_name")).click();
		wd.findElement(By.name("group_name")).clear();
		wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
		wd.findElement(By.name("group_header")).clear();
		wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
		wd.findElement(By.name("group_footer")).clear();
		wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
	}

	public void initGroupCreation() {
		wd.findElement(By.name("new")).click();
	}

	public void gotoGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}

	public void stop() {
		wd.quit();
	}

	public void deleteSelectedGroups() {
		wd.findElement(By.name("delete")).click();
	}

	public void selectGroup() {
		wd.findElement(By.name("selected[]")).click();
	}

	public void goToContactPage() {
		wd.findElement(By.linkText("home")).click();
	}

	public void submitContactCreation() {
		wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
	}

	public void fillFormContact(GroupContactData parameterObject) {
		wd.findElement(By.name("firstname")).click();
		wd.findElement(By.name("firstname")).clear();
		wd.findElement(By.name("firstname")).sendKeys(parameterObject.getFirstName());
		wd.findElement(By.name("lastname")).click();
		wd.findElement(By.name("lastname")).clear();
		wd.findElement(By.name("lastname")).sendKeys(parameterObject.getLastName());
		wd.findElement(By.name("mobile")).click();
		wd.findElement(By.name("mobile")).clear();
		wd.findElement(By.name("mobile")).sendKeys(parameterObject.getPhoneNumber());
		wd.findElement(By.name("email")).click();
		wd.findElement(By.name("email")).clear();
		wd.findElement(By.name("email")).sendKeys(parameterObject.getEmailAddress());
	}

	public void addNewContact() {
		wd.findElement(By.linkText("add new")).click();
	}

	public void deleteSelectedContact() {
		wd.findElement(By.xpath("//input[@value='Delete']")).click();
		wd.findElement(By.xpath("//body")).click();
	}

	public void selectContact() {
		wd.findElement(By.name("selected[]")).click();
	}

}
