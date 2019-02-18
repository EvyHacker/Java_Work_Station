package usa.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import usa.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        //int before = app.getGroupHelper().getGroupCount();
        GroupData group= new GroupData("test1", null, null);
        app.getGroupHelper().createGroup(group);
        // int after = app.getGroupHelper().getGroupCount();
        //Assert.assertEquals(after, before + 1);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() +1);


        int max = 0;
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        group.setId(max);
            before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    }
}