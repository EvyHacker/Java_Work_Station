package usa.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationClass extends TestBase {
	

	@Test
	public void testGroupCreationContact() {
		app.addNewContact();
		app.fillFormContact(new GroupContactData("Ievgeniia", "Gaidarenko", "571-241-6524", "gaidarenko1241@gmail.com"));
		app.submitContactCreation();
		app.goToContactPage();
	
	}
}
