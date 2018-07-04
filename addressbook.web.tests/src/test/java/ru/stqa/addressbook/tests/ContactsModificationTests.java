package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase {

    @Test
    public void contactsModificationTests() {
        app.getNavigationHelper().gotoContactPage();

        if (!app.getContactsHelper().isThereAContact()) {
            app.getContactsHelper().createContact(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        }
        app.getNavigationHelper().gotoContactPage();
        List<ContactsData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().editContact(before.size()-1);
        ContactsData contacts = new ContactsData(before.get(before.size()-1).getId(),"Test First name-5", "Test Middle name", "Test Last name-4", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", null);
        app.getContactsHelper().fillContactData(contacts, false);
        app.getContactsHelper().submitContactModification();
        app.getContactsHelper().returntoContactPage();
        List<ContactsData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() -1);

        before.add(contacts);

        Comparator<? super ContactsData> byId = (g1 , g2 )-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);




    }
}
