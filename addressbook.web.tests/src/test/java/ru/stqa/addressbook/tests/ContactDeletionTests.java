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
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                    withGroup("Test1"));
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
