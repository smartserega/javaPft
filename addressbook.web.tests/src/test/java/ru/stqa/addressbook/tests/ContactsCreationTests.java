package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        ContactsData contacts = new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1");

        app.contacts().create(contacts);
        app.goTo().contactPage();
        Set<ContactsData> after = app.contacts().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contacts.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test
    public void testContactsCreationBadTests() {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        ContactsData contacts = new ContactsData().withFirstName("FirstName'").withMiddleName("MiddleName").
                withLastName("LastName'").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                withGroup("Test1");

        app.contacts().create(contacts);
        app.goTo().contactPage();
        assertThat(app.contacts().getContactsCount(), equalTo(before.size()));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before));

    }
}
