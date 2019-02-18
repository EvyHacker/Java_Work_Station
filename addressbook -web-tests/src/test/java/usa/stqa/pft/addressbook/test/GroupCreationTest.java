package usa.stqa.pft.addressbook.test;


import org.testng.Assert;
import org.testng.annotations.Test;

import usa.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        //int before = app.group().getGroupCount();
        GroupData group= new GroupData().withName("test2");
        app.group().create(group);
        // int after = app.group().getGroupCount();
        //Assert.assertEquals(after, before + 1);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() +1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }
}