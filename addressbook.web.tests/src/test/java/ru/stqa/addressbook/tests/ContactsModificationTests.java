package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Set;

public class ContactsModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().contactPage();

        if (!app.contacts().isThereAContact()) {
            app.contacts().create(new ContactsData().withFirstName("Test First name-2").withMiddleName("Test Middle name")
                    .withLastName("Test Last Name").withNickname("Test Nickname").withTitle("Test Title").withCompane("Test Compane").withGroup("Test"));
        }
    }

    @Test
    public void contactsModificationTests() {
        app.goTo().contactPage();
        Set<ContactsData> before = app.contacts().all();
        ContactsData modifiedContact = before.iterator().next();
        int index = before.size() - 1;
        ContactsData contacts = new ContactsData().withId(modifiedContact.getId()).withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1");


        app.contacts().modifyContact(contacts);
        Set<ContactsData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedContact);
        before.add(contacts);
        Assert.assertEquals(before, after);
    }


}
