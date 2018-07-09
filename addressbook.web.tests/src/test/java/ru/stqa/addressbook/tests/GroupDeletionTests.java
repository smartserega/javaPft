package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().groupPage();
        if (app.group().list().size() ==0) {
            app.group().create(new GroupData().withName("Test1"));
        }
    }


    @Test
    public void testGroupDeletion() {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("Test1"));
        }
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);

    }



}
