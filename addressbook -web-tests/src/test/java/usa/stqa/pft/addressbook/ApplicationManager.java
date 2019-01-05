package usa.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

	protected void init() {
		System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook/edit.php");
		login("admin", "secret");
	}

	private void login(String username, String password) {
		wd.findElement(By.name("user")).clear();
		wd.findElement(By.name("user")).sendKeys(username);
		wd.findElement(By.name("pass")).clear();
		wd.findElement(By.name("pass")).sendKeys(password);
		wd.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"))
				.click();
	}

	protected void returnToGroupPage() {
		wd.findElement(By.linkText("Logout")).click();
	}

	protected void submitGroupCreation() {
		wd.findElement(By.name("submit")).click();
	}

	protected void fillGroupForm(GroupData groupData) {
		wd.findElement(By.name("group_name")).click();
		wd.findElement(By.name("group_name")).clear();
		wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
		wd.findElement(By.name("group_header")).clear();
		wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
		wd.findElement(By.name("group_footer")).clear();
		wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
	}

	protected void initGroupCreation() {
		wd.findElement(By.name("new")).click();
	}

	protected void gotoGroupPage() {
		wd.findElement(By.linkText("groups")).click();
	}

	protected void stop() {
		wd.quit();
	}

	protected void deleteSelectedGroups() {
		wd.findElement(By.name("delete")).click();
	}

	protected void selectGroup() {
		wd.findElement(By.name("selected[]")).click();
	}

	protected void goToContactPage() {
		wd.findElement(By.linkText("home")).click();
	}

	protected void submitContactCreation() {
		wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
	}

	protected void fillFormContact(GroupContactData parameterObject) {
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

	protected void addNewContact() {
		wd.findElement(By.linkText("add new")).click();
	}

	protected void deleteSelectedContact() {
		wd.findElement(By.xpath("//input[@value='Delete']")).click();
		wd.findElement(By.xpath("//body")).click();
	}

	protected void selectContact() {
		wd.findElement(By.name("selected[]")).click();
	}

}
