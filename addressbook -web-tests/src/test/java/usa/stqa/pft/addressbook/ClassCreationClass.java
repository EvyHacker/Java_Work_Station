package usa.stqa.pft.addressbook;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClassCreationClass {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login("admin", "secret");
	}

	private void login(String username, String password) {
		driver.get("http://localhost/addressbook/index.php");
		driver.findElement(By.name("user")).clear();
		driver.findElement(By.name("user")).sendKeys(username);
		driver.findElement(By.name("pass")).clear();
		driver.findElement(By.name("pass")).sendKeys(password);
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"))
				.click();
	}

	@Test
	public void testClassCreation() {
		addNewContact();
		fillFormContact(new GroupContactData("Ievgeniia", "Gaidarenko", "571-241-6524", "gaidarenko1241@gmail.com"));
		submitNewContact();
		logout();
	}

	private void logout() {
		driver.findElement(By.linkText("Logout")).click();
	}

	private void submitNewContact() {
		driver.findElement(
				By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"))
				.click();
	}

	private void fillFormContact(GroupContactData parameterObject) {
		driver.findElement(By.name("firstname")).click();
		driver.findElement(By.name("firstname")).clear();
		driver.findElement(By.name("firstname")).sendKeys(parameterObject.getFirstName());
		driver.findElement(By.name("lastname")).click();
		driver.findElement(By.name("lastname")).clear();
		driver.findElement(By.name("lastname")).sendKeys(parameterObject.getLastName());
		driver.findElement(By.name("mobile")).click();
		driver.findElement(By.name("mobile")).clear();
		driver.findElement(By.name("mobile")).sendKeys(parameterObject.getMobileNumber());
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(parameterObject.getEmailAddress());
	}

	private void addNewContact() {
		driver.findElement(By.linkText("add new")).click();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
	
