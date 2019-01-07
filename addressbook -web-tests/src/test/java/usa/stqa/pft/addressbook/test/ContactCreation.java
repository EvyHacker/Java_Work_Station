package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.GroupContactData;
import usa.stqa.pft.addressbook.model.GroupData;

public class ContactCreation extends TestBase {


    @Test
    public void testGroupCreationContact() {
        app.getContactHelper().addNewContact();
        app.getContactHelper().fillFormContact(new GroupContactData("Ievgeniia", "Gaidarenko", "571-241-6524", "gaidarenko1241@gmail.com"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationContactHelper().goToContactPage();

    }
}
