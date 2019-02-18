package usa.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
	WebDriver wd;

	private String browser;
	private ContactHelper contactHelper;
	private GroupHelper groupHelper;
	private NavigationHelper navigationHelper;
	private SessionHelper sessionHelper;

	public ApplicationManager(String browser) {

		this.browser = browser;
	}

	public ContactHelper contact() {
		return contactHelper;
	}

	public GroupHelper group() {
		return groupHelper;
	}

	public NavigationHelper goTo() {
		return navigationHelper;
	}

	public void init() {
		System.setProperty("webdriver.chrome.driver",
				"/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
		System.setProperty("webdriver.gecko.driver",
				"/Users/ievgeniiagaidarenko/JAVA/geckodriver ");
		if (browser.equals(BrowserType.CHROME)) {
			wd = new ChromeDriver();
		} else if (browser.equals(BrowserType.FIREFOX)) {
			wd = new FirefoxDriver();
		} else if (browser.equals(BrowserType.SAFARI)) {
			wd = new SafariDriver();
		}

		wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		wd.get("http://localhost/addressbook");
		contactHelper = new ContactHelper(wd);
		groupHelper = new GroupHelper(wd);
		navigationHelper = new NavigationHelper(wd);
		sessionHelper = new SessionHelper(wd);
		sessionHelper.login("admin", "secret");
	}

	public void stop() {
		wd.quit();
	}

}