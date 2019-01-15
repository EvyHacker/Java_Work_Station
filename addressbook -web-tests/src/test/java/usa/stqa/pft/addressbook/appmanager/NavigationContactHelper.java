package usa.stqa.pft.addressbook.appmanager;

//import com.sun.tools.corba.se.idl.toJavaPortable.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationContactHelper extends HelperBase {

    public NavigationContactHelper(WebDriver wd) {
        super(wd);

    }

    public void goToContactPage() {
        click(By.linkText("home"));
    }

}
