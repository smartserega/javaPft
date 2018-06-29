package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.getNavigationHelper().gotoContactPage();
        List<ContactsData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().createContact(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        app.getNavigationHelper().gotoContactPage();
        List<ContactsData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);
    }
}
