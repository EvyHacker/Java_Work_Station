package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.GroupData;
import usa.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.group().all().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size()-1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withoutAdded(deletedGroup)));
        verifyGroupListInUi();
        }


}

