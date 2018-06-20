package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactsDeletionTest (){
        app.getNavigationHelper().gotoContactPage();
        app.getGroupHelper().selectContact();
        app.getGroupHelper().editContact();
        app.getContactsHelper().deleteContact();
        app.getGroupHelper().returntoContactPage();

    }
}
