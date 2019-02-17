package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;
import usa.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase {

	@Test
	public void testContactModification() throws InterruptedException {
		app.getNavigationContactHelper().goToContactPage();
		if (! app.getGroupHelper().isThereAGroup()) {//Добавлена проверка и обеспечение предусловий выполнения GroupModificationTests и GroupDeletionTests
			app.getGroupHelper().createGroup(new GroupData("test1", null, null));
		}
		app.getContactHelper().selectContact();
		app.getContactHelper().initContactModification();
		app.getContactHelper()
				.fillFormContact(new ContactData("Evy", "Klimovich",
						"571-241-6524", "gaidarenko1241@gmail.com", null),
						false);
		Thread.sleep(3000);
		app.getContactHelper().submitContactModification();
		app.getNavigationContactHelper().goToContactPage();
	}
}