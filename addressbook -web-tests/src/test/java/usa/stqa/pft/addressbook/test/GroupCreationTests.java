package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

import usa.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));

    }
}