package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.HashSet;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.getNavigationHelper().gotoContactPage();
        List<ContactsData> before = app.getContactsHelper().getContactList();
        ContactsData contacts = new ContactsData("Test First name-1", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1");
        app.getContactsHelper().createContact(contacts);
        app.getNavigationHelper().gotoContactPage();
        List<ContactsData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        contacts.setId(max);
        before.add(contacts);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
