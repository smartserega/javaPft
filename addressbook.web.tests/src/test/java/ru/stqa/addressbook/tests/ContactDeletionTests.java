package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().contactPage();

        if (!app.contacts().isThereAContact()) {
            app.contacts().create(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1"));
        }
    }

    @Test
    public void contactsDeletionTest() {
        app.goTo().contactPage();
        List<ContactsData> before = app.contacts().list();
        int index = before.size() - 1;
        app.goTo().contactPage();
        app.contacts().delete(index);
        List<ContactsData> after = app.contacts().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }


}
