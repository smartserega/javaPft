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
        ContactsData contacts = new ContactsData().withFirstName("Test First name-2").withMiddleName("Test Middle name")
                .withLastName("Test Last Name").withNickname("Test Nickname").withTitle("Test Title").withCompane("Test Compane").withGroup("Test");

        app.contacts().create(contacts);
        app.goTo().contactPage();
        List<ContactsData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size() + 1);


        contacts.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contacts);
        Comparator<? super ContactsData> byId = (g1 , g2 )-> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
