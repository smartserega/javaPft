package ru.stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
//        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.xml")));
        String xml = "";
        String line = reader.readLine();

        while (line != null) {
//            list.add(new Object[]{new ContactsData().withGroup("Test1")});
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactsData.class);
        List<ContactsData> contacts = (List<ContactsData>) xStream.fromXML(xml);

        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContacts")
    public void testContactsCreationTests(ContactsData contacts) {
        app.goTo().contactPage();
        Contacts before = app.contacts().all();
        app.contacts().create(contacts.withGroup("Test1"));
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
