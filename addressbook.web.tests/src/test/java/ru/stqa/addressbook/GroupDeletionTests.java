package ru.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }

}
