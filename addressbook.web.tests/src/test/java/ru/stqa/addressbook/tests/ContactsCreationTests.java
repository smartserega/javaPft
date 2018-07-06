package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.goTo().contactPage();
        List<ContactsData> before = app.contacts().list();
        ContactsData contacts = new ContactsData("Test First name-2", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1");
        app.contacts().create(contacts);
        app.goTo().contactPage();
        List<ContactsData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        contacts.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contacts);
        Comparator<? super ContactsData> byId = (g1 , g2 )-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
