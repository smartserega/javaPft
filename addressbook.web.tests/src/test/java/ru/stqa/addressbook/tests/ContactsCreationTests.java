package ru.stqa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/photo.png");
        list.add(new Object[]{new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                withGroup("Test1").withPhoto(photo)});
        list.add(new Object[]{new ContactsData().withFirstName("FirstName-2").withMiddleName("MiddleName-2").
                withLastName("LastName-2").withNickname("nickname-2").withTitle("Title-2").withCompane("company-2").
                withAddress("address-2").withMobile("+79991119990").withHomePhone("+79991119990").withEmail("E-mail@E-mail.ru2").
                withGroup("Test2").withPhoto(photo)});
        list.add(new Object[]{new ContactsData().withFirstName("FirstName-3").withMiddleName("MiddleName-3").
                withLastName("LastName-3").withNickname("nickname-3").withTitle("Title-3").withCompane("company-3").
                withAddress("address-2").withMobile("+79991119990").withHomePhone("+79991119990").withEmail("E-mail@E-mail.ru2").
                withGroup("Test2").withPhoto(photo)});
        return list.iterator();
    }


    @Test(dataProvider = "validContacts")
    public void testContactsCreationTests(ContactsData contacts) {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        app.contacts().create(contacts);
        app.goTo().contactPage();
        Set<ContactsData> after = app.contacts().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contacts.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test(enabled = false)
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
