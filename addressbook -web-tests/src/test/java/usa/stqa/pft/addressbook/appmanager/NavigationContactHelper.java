package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationContactHelper {
	
	private ChromeDriver wd;
	

	public NavigationContactHelper(ChromeDriver wd) {
		this.wd = wd;
		
	}

	public void goToContactPage() {
		wd.findElement(By.linkText("home")).click();
	}

}
