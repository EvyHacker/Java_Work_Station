package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import usa.stqa.pft.addressbook.model.GroupContactData;

public class ContactHelper extends HelperBase{


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

    public void deleteSelectedContact() throws InterruptedException {
        click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
        click(By.name("update"));

    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

}
