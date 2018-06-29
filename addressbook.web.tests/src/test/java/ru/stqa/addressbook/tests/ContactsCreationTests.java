package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.getNavigationHelper().gotoContactPage();
        int before = app.getContactsHelper().getContactsCount();
        app.getContactsHelper().createContact(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        app.getNavigationHelper().gotoContactPage();
        int after = app.getContactsHelper().getContactsCount();
        Assert.assertEquals(after, before +1);
    }
}
