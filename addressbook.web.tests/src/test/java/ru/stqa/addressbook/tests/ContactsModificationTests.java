package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().contactPage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                    withGroup("Test1"));
        }
    }


    @Test
    public void contactsModificationTests() {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        ContactsData modifiedContact = before.iterator().next();
        ContactsData contacts = new ContactsData().withId(modifiedContact.getId()).withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                withGroup("Test1");


        app.contacts().modifyContact(contacts);
        assertThat(app.contacts().getContactsCount(), equalTo(before.size()));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.withOutAdded(modifiedContact).withAdded(contacts)));


    }


}
