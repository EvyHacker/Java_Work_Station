package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

	@Test
	public void testGroupDeletion() {
		app.gotoGroupPage();
		app.selectGroup();
		app.deleteSelectedGroups();
		app.returnToGroupPage();
	}

}