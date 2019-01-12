package usa.stqa.pft.addressbook.appmanager;

//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import usa.stqa.pft.addressbook.model.GroupContactData;

import java.text.MessageFormat;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

	static int Message;
    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillFormContact(GroupContactData parameterObject) {
        type(By.name("firstname"), parameterObject.getFirstName());
        type(By.name("lastname"), parameterObject.getLastName());
        type(By.name("mobile"), parameterObject.getPhoneNumber());
        type(By.name("email"), parameterObject.getEmailAddress());
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContact()  {
        click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();

    }

    public void selectContact() {
        click(By.name("selected[]"));

    }

    public void initContactModification() {
        click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));

    }

    public void submitContactModification() {
        click(By.name("update"));
    }
    public void getMessage() throws InterruptedException {
    	String Message = wd.findElement(By.xpath("//div[@class='msgbox']")).getText();
    	Thread.sleep(3000);
    	assertTrue(Message.contains("Record successful deleted"));
    	System.out.println(MessageFormat.format("Congrats {0}", Message));

    }
}