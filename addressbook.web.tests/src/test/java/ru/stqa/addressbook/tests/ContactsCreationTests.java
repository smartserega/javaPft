package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Set;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.goTo().contactPage();
        Set<ContactsData> before = app.contacts().all();
        ContactsData contacts = new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1");

        app.contacts().create(contacts);
        app.goTo().contactPage();
        Set<ContactsData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contacts.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contacts);
        Assert.assertEquals(before, after);

    }
}
