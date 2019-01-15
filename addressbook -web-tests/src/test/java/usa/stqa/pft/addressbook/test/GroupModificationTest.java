package usa.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import usa.stqa.pft.addressbook.model.GroupData;


public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() throws InterruptedException {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        Thread.sleep(3000);
        app.getGroupHelper().initGroupModification();
        Thread.sleep(3000);
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "test5", "test6"));
        Thread.sleep(3000);
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToGroupPage();

    }
}


