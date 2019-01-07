package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import usa.stqa.pft.addressbook.model.GroupData;

public class HelperBase {

    protected ChromeDriver wd;

	protected void click(By locator) {
	    wd.findElement(locator).click();
	}

	protected void type(By locator, String text) {
	    click(locator);
	    wd.findElement(locator).clear();
	    wd.findElement(locator).sendKeys(text);
	}


}
