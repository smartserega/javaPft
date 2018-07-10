package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Set;

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
        Set<ContactsData> before = app.contacts().all();
        ContactsData deletedContact = before.iterator().next();
        int index = before.size() - 1;
        app.goTo().contactPage();
        app.contacts().delete(deletedContact);
        Set<ContactsData> after = app.contacts().all();
        Assert.assertEquals(after.size(), index);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
