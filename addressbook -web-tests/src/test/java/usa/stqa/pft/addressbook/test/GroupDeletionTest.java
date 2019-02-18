package usa.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.*;
import usa.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group()
                    .create(new GroupData("test1", null, null));

        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().list();
        int index = before.size() -1;
       // int before = app.group().getGroupCount();
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);
//        int after = app.group().getGroupCount();
//        Assert.assertEquals(after, before - 1);

        before.remove(index);
        //for (int i = 0; i< after.size(); i++){
          //  Assert.assertEquals(before.get(i), after.get(i));
        Assert.assertEquals(before, after);
        }


}

