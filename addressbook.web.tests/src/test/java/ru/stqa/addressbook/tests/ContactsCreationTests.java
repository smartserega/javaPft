package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

public class ContactsCreationTests extends TestBase {

    @Test
    public void testContactsCreationTests() {
        app.goTo().contactPage();
        List<ContactsData> before = app.contacts().list();
        ContactsData contacts = new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1");

        app.contacts().create(contacts);
        app.goTo().contactPage();
        List<ContactsData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        contacts.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contacts);
        Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
