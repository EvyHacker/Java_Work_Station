package usa.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.GroupData;
import usa.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTest extends TestBase {

	@BeforeMethod
	public void ensurePreconditions() {
		if (app.db().groups().size() == 0){
			app.goTo().groupPage();
			app.group().create(new GroupData().withName("test1"));
		}
	}
		@Test
		public void testGroupModification() {
			Groups before = app.db().groups();
			GroupData modifiedGroup = before.iterator().next();
			GroupData group = new GroupData()
					.withId(modifiedGroup.getId()).withName("test4").withHeader("test5").withFooter("test6");
			app.goTo().groupPage();
			app.group().modify(group);
			assertThat(app.group().count(), equalTo(before.size()));
			Groups after = app.db().groups();
			assertThat(after, equalTo(before.withoutAdded(modifiedGroup).withAdded(group)));
			verifyGroupListInUi();
		}


}
