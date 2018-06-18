package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.getContactsHelper().initAddNewContact();
        app.getContactsHelper().fillContactData(new ContactsData("Test First name", "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru"));
        app.getContactsHelper().initContactCreation();
    }
}
