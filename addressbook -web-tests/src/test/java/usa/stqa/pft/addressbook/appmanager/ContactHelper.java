package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import usa.stqa.pft.addressbook.model.GroupContactData;

public class ContactHelper {
	private ChromeDriver wd;

	public ContactHelper(ChromeDriver wd) {
		this.wd = wd;
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

	public void deleteSelectedContact() throws InterruptedException {
		wd.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img")).click();
		wd.findElement(By.name("update")).click();
//		Thread.sleep(4000);
//		wd.findElement(By.xpath("//body")).click();
	
	}

	public void selectContact() {
		wd.findElement(By.name("selected[]")).click();
	}

}
