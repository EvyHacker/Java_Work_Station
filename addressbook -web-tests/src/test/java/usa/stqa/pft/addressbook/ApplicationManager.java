package usa.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

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

}
