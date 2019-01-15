package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {


    @Test
    public void testContactModification() throws InterruptedException {
    app.getNavigationContactHelper().goToContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillFormContact(new ContactData("Evy", "Klimovich", "571-241-6524", "gaidarenko1241@gmail.com", null), false);
    Thread.sleep(3000);
    app.getContactHelper().submitContactModification();
    app.getNavigationContactHelper().goToContactPage();
    }
}