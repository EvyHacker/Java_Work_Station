package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    ChromeDriver wd;

    private ContactHelper contactHelper;

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    private GroupHelper groupHelper ;
    
    public GroupHelper getGroupHelper() {
    	return groupHelper;
    }
    
    
   // private HelperBase groupHelper;

//    public HelperBase getGrouphelper() {
//        return groupHelper;
//    }

    private NavigationHelper navigationHelper;

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    private NavigationContactHelper navigationContactHelper;

    public NavigationContactHelper getNavigationContactHelper() {
        return navigationContactHelper;
    }

    private SessionHelper sessionHelper;

    public static boolean isAlertPresent(ChromeDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/ievgeniiagaidarenko/JAVA/chromedriver 2");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/edit.php");
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationContactHelper = new NavigationContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

}
