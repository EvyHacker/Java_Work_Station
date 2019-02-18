package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

	@Test
	public void testGroupModification() {
		app.getNavigationHelper().gotoGroupPage();
		if (!app.getGroupHelper().isThereAGroup()) {
			app.getGroupHelper()
					.createGroup(new GroupData("test1", null, null));
		}
		List<GroupData> before = app.getGroupHelper().getGroupList();
		//int before = app.getGroupHelper().getGroupCount();
		app.getGroupHelper().selectGroup(before.size()-1);
		app.getGroupHelper().initGroupModification();
		GroupData group = new GroupData( before.get(before.size()-1).getId(),"test4", "test5", "test6");
		app.getGroupHelper()
				.fillGroupForm(group);
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupPage();
		List<GroupData> after = app.getGroupHelper().getGroupList();
		Assert.assertEquals(after.size(), before.size());
//		int after = app.getGroupHelper().getGroupCount();
//		Assert.assertEquals(after, before);


		before.remove(before.size()-1);
		before.add(group);
		Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

	}
}
