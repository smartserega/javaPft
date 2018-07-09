package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Comparator;
import java.util.List;

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
        List<ContactsData> before = app.contacts().list();
        int index = before.size() - 1;
        ContactsData contacts = new ContactsData().withId(before.get(index).getId()).withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1");


        app.contacts().modifyContact(index, contacts);
        List<ContactsData> after = app.contacts().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);

        before.add(contacts);

        Comparator<? super ContactsData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
