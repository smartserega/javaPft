package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();

        if(!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size());
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("Test1", "Test2", "Test3"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }

}
