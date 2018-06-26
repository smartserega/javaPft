package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void contactsDeletionTest (){
        app.getNavigationHelper().gotoContactPage();
        if(!app.getContactsHelper().isThereAContact()){
            app.getContactsHelper().createContact(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        }
        app.getNavigationHelper().gotoContactPage();
        app.getGroupHelper().selectContact();
        app.getGroupHelper().editContact();
        app.getContactsHelper().deleteContact();
        app.getGroupHelper().returntoContactPage();

    }
}
