package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

public class ContactsModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().contactPage();

        if (!app.contacts().isThereAContact()) {
            app.contacts().create(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        }
    }

    @Test
    public void contactsModificationTests() {
        app.goTo().contactPage();
        List<ContactsData> before = app.contacts().list();
        int index = before.size() - 1;
        ContactsData contacts = new ContactsData(before.get(index).getId(), "Test First name-5", "Test Middle name", "Test Last name-4", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", null);
        app.contacts().modifyContact(index, contacts);
        List<ContactsData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);

        before.add(contacts);

        Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);


    }


}
