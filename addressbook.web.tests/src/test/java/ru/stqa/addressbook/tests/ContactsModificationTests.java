package ru.stqa.addressbook.tests;

import org.testng.Assert;
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
            app.contacts().create(new ContactsData().withFirstName("Test First name-2").withMiddleName("Test Middle name")
                    .withLastName("Test Last Name").withNickname("Test Nickname").withTitle("Test Title").withCompane("Test Compane").withGroup("Test"));
        }
    }

    @Test
    public void contactsModificationTests() {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        ContactsData modifiedContact = before.iterator().next();
        ContactsData contacts = new ContactsData().withId(modifiedContact.getId()).withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1");


        app.contacts().modifyContact(contacts);
        Contacts after = app.contacts().all();

        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOutAdded(modifiedContact).withAdded(contacts)));
    }


}
