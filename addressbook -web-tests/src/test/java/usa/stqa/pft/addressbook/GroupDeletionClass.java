package usa.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GroupDeletionClass extends TestBase{
	WebDriver wd;
	private boolean acceptNextAlert = true;
	@BeforeClass
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
		wd = new ChromeDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testGroupDeletionContact() {
		wd.get("http://localhost/addressbook/group.php");
		wd.findElement(By.name("user")).clear();
		wd.findElement(By.name("user")).sendKeys("admin");
		wd.findElement(By.name("pass")).click();
		wd.findElement(By.name("pass")).clear();
		wd.findElement(By.name("pass")).sendKeys("secret");
		wd.findElement(By.xpath("//input[@value='Login']")).click();
		wd.findElement(By.linkText("home")).click();
		wd.findElement(By.name("selected[]")).click();
		acceptNextAlert = true;
		wd.findElement(By.xpath("//input[@value='Delete']")).click();
		assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
		wd.findElement(By.xpath("//body")).click();
		wd.findElement(By.id("top")).click();
		wd.findElement(By.linkText("Logout")).click();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		wd.quit();
	
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = wd.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
