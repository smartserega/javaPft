package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactsDeletionTest (){
        app.getNavigationHelper().gotoContactPage();
        int before = app.getContactsHelper().getContactsCount();
        if(!app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().createContact(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        }
        app.getNavigationHelper().gotoContactPage();
        app.getContactsHelper().selectContact(before -1);
        app.getContactsHelper().editContact();
        app.getContactsHelper().deleteContact();
        app.getContactsHelper().returntoContactPage();
        int after = app.getContactsHelper().getContactsCount();
        Assert.assertEquals(after, before -1);
    }
}
