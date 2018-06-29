package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactsDeletionTest (){
        app.getNavigationHelper().gotoContactPage();

        if(!app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().createContact(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        }
        app.getNavigationHelper().gotoContactPage();
        List<ContactsData> before = app.getContactsHelper().getContactList();
        app.getNavigationHelper().gotoContactPage();
        app.getContactsHelper().selectContact(before.size()-1);
        app.getContactsHelper().editContact();
        app.getContactsHelper().deleteContact();
        app.getContactsHelper().returntoContactPage();
        List<ContactsData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);
    }
}
