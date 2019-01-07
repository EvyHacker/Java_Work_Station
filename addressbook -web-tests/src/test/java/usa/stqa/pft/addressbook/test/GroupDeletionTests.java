package usa.stqa.pft.addressbook.test;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGrouphelper().selectGroup();
        app.getGrouphelper().deleteSelectedGroups();
        app.getGrouphelper().returnToGroupPage();
    }

}