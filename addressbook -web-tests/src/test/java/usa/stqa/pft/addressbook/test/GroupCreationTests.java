package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;

import usa.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGrouphelper().initGroupCreation();
        app.getGrouphelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGrouphelper().submitGroupCreation();
        app.getGrouphelper().returnToGroupPage();
    }
}