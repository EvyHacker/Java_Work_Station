package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase {

	@Test
	public void testGroupModification() throws InterruptedException {
		app.getNavigationHelper().gotoGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper()
					.createGroup(new GroupData("test1", null, null));
		}
		app.getGroupHelper().selectGroup();
		Thread.sleep(3000);
		app.getGroupHelper().initGroupModification();
		Thread.sleep(3000);
		app.getGroupHelper()
				.fillGroupForm(new GroupData("test4", "test5", "test6"));
		Thread.sleep(3000);
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupPage();

	}
}
