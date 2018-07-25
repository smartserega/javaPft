package ru.stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validContactsFromXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();

            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactsData.class);
            List<ContactsData> contacts = (List<ContactsData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }

    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.json")))) {
            String json = "";
            String line = reader.readLine();

            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactsData> groups = gson.fromJson(json, new TypeToken<List<ContactsData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }

    }


    @BeforeMethod
    public void ensurePrecondtions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testContactsCreationTests(ContactsData contacts) {
        Groups groups = app.db().groups();
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        app.contacts().create(contacts.inGroup(groups.iterator().next()));
        app.goTo().contactPage();
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contacts.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUi();


    }

    @Test(enabled = false)
    public void testContactsCreationBadTests() {
        Groups groups = app.db().groups();
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        ContactsData contacts = new ContactsData().withFirstName("FirstName'").withMiddleName("MiddleName").
                withLastName("LastName'").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                inGroup(groups.iterator().next());

        app.contacts().create(contacts);
        app.goTo().contactPage();
        assertThat(app.contacts().getContactsCount(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUi();

    }
}
