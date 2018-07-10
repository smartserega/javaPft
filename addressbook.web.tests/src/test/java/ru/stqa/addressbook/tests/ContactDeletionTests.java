package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().contactPage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                    withGroup("Test1"));
        }
    }

    @Test
    public void contactsDeletionTest() {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        ContactsData deletedContact = before.iterator().next();

        app.goTo().contactPage();
        app.contacts().delete(deletedContact);
        Contacts after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.withOutAdded(deletedContact)));
    }


}
