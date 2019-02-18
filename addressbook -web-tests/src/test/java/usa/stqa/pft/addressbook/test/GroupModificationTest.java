package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		app.goTo().groupPage();
		if (app.group().list().size() == 0) {
			app.group().create(new GroupData("test1", null, null));
		}
	}
		@Test
		public void testGroupModification() {
			List<GroupData> before = app.group().list();
			//int before = app.group().getGroupCount();
			int index = before.size() - 1;
			GroupData group = new GroupData(before.get(index).getId(), "test4", "test5", "test6");
			app.group().modify(index, group);
			List<GroupData> after = app.group().list();
			Assert.assertEquals(after.size(), before.size());
//		int after = app.group().getGroupCount();
//		Assert.assertEquals(after, before);

			before.remove(index);
			before.add(group);
			Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
			before.sort(byId);
			after.sort(byId);
			Assert.assertEquals(before, after);
		}
}
