package usa.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

	static int Message;

	public ContactHelper(WebDriver wd) {
		super(wd);
	}

	public void submitContactCreation() {
		click(By.xpath("(//input[@name='submit'])[2]"));
	}

	public void create(ContactData contactData, boolean creation) {
		addNewContact();
		fillFormContact(contactData, creation);
		submitContactCreation();
		contactCache = null;
		goToContactPage();

	}
	public void modify(ContactData contact) {
		selectContactById(contact.getId());
		initContactModification(contact.getId());
		fillFormContact(contact, false);
		submitContactModification();
		contactCache = null;
		goToContactPage();
	}

	public void delete(ContactData contact) {
		selectContactById(contact.getId());
		deleteSelectedContact();
		messageCompleteDeletionContacts();
		contactCache = null;
		goToContactPage();
	}

	private void selectContactById(int id) {
		wd.findElement(By.name("selected[]")).click();
	}
	private void selectToGroup(Integer idGroup) {
		new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(idGroup));
	}

	private void CopyToGroup() {
		wd.findElement(By.name("add")).click();
	}

	public void messageCompleteAddedContactToGroup() {
		wd.findElement(By.xpath("//*[contains(text(), 'Users added')]"));
	}

		private void goToContactPage() {
		wd.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[1]/a")).click();
	}

	public void fillFormContact(ContactData contactData, boolean creation) {
		type(By.name("firstname"), contactData.getFirstName());
		type(By.name("lastname"), contactData.getLastName());
		type(By.name("mobile"), contactData.getPhoneNumber());
		type(By.name("email"), contactData.getEmailAddress());
		attach(By.name("photo"), contactData.getPhoto());

//		if (creation) {
//			new Select(wd.findElement(By.name("new_group")))
//					.selectByVisibleText(contactData.getGroup());
//		} else {
//			Assert.assertFalse(isElementPresent(By.name("new_group")));
//		}
	}

	public void addNewContact() {
		click(By.linkText("add new"));
	}

	public void deleteSelectedContact() {
		click(By.xpath("//*[@id=\"content\"]/form[2]/div[2]/input"));
		wd.switchTo().alert().accept();
	}
	public void addToGroup(ContactData contact, Integer idGroup) {
		selectContactById(contact.getId());
		selectToGroup(idGroup);
		CopyToGroup();
		messageCompleteAddedContactToGroup();
	}

	private void selectDeleteToGroup(Integer idGroup) {
		new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(idGroup));
	}

	private void removeFromGroup() {
		wd.findElement(By.name("remove")).click();
	}

	public void messageRemovedContactFromGroup() {
		wd.findElement(By.xpath("//*[contains(text(), 'Users removed')]"));
	}

	public void deleteFromGroup(ContactData contact, Integer idGroup) {
		selectDeleteToGroup(idGroup);
		selectContactById(contact.getId());
		removeFromGroup();
		messageRemovedContactFromGroup();
	}

	public void messageCompleteDeletionContacts() {
		wd.findElement(By.xpath("//*[contains(text(), 'Record successful deleted')]"));
	}

//	public void initContactModification() {
//		click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[8]/a/img"));
//
//	}

	public void submitContactModification() {
		click(By.name("update"));
	}

	public boolean isThereAContact() {
		return isElementPresent(By.name("selected[]"));
	}

	public int count() {
		return wd.findElements(By.name("selected[]")).size();
	}

	private Contacts contactCache = null;

	public Contacts all() {
		if (contactCache != null){
			return new Contacts(contactCache);
		}
		contactCache = new Contacts();
		List<WebElement> rows = wd.findElements(By.name("entry"));
		//for (WebElement element : elements) {
		for (WebElement row : rows){
			List<WebElement> cells = row.findElements(By.tagName("td"));
			int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
			//int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
			//List<WebElement> cells = row.findElements(By.tagName("input")).getAttribute("Value");
			//String lastName = element.findElement(By.xpath("td[2]")).getText();
			//String firstName = element.findElement(By.xpath("td[3]")).getText();
			String lastName = cells.get(1).getText();
			String firstName = cells.get(2).getText();
			//String[] phones = cells.get(5).getText().split("\n");
			String allPhones = cells.get(5).getText();
			String allAddresses = cells.get(3).getText();
			String allEmails = cells.get(4).getText();
			contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
					.withAllPhones(allPhones).withAllAddresses(allAddresses).withAllEmails(allEmails));
		}

		return new Contacts(contactCache);
	}

	public ContactData infoFromEditForm(ContactData contact) {
		initContactModification(contact.getId());
		String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
		String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
		String homePhone  = wd.findElement(By.name("home")).getAttribute("value");
		String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
		String workPhone = wd.findElement(By.name("work")).getAttribute("value");
		String emailAddress = wd.findElement(By.name("email")).getAttribute("value");
		String emailAddress2 = wd.findElement(By.name("email2")).getAttribute("value");
		String emailAddress3 = wd.findElement(By.name("email3")).getAttribute("value");
		String firstAddress = wd.findElement(By.name("address")).getAttribute("value");
		String secondAddress = wd.findElement(By.name("address2")).getAttribute("value");
		wd.navigate().back();
		return  new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
				.withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
				.withFirstAddress(firstAddress).withSecondAddress(secondAddress).withEmailAddress(emailAddress)
				.withEmailAddress2(emailAddress2).withEmailAddress3(emailAddress3);

	}

	private void initContactModification(int id) {//ответ на зд№9
		wd.findElement(By.xpath("//a[@href='edit.php?id=" + id +"']")).click();
//		WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value'%s]", id)));
//		WebElement row = checkbox.findElement(By.xpath("./../.."));
//		List<WebElement> cells = row.findElements(By.tagName("td"));
//		cells.get(7).findElement(By.tagName("a")).click();
		//wd.findElement(By.cssSelector(String.format("//input[@value='%s']/../../td[8]/a", id))).click;
		//wd.findElement(By.cssSelector(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click;
		//wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click;
	}
}
