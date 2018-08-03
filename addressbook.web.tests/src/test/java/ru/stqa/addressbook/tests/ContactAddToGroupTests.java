package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        Groups groups = app.db().groups();
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                    inGroup(groups.iterator().next()));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
    }

    @Test
    public void addContactToGroupTests() {

        int beforeNumberOfConnections = app.db().connectionsNumber();

        ContactsData freeContact = findContactFreeContactForAdd();
        GroupData freeGroup = findGroupForFreeContact();

        findGroupForFreeContact();
        addContactToGroup(freeContact, freeGroup);


        int afterNumberOfConnections = app.db().connectionsNumber();
        assertThat(afterNumberOfConnections, equalTo(beforeNumberOfConnections + 1));
    }

    private void addContactToGroup(ContactsData freeContact, GroupData freeGroup) {
    }

    private GroupData findGroupForFreeContact() {
        return null;
    }

    private ContactsData findContactFreeContactForAdd() {
        return null;
    }







































    @Test(enabled = false)
    public void BadVetsionOFaddContactToGroupTests() {
        int unicNumber = app.contacts().randomNumber();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("TestAddContactGroup-" + unicNumber));

        app.goTo().contactPage();
        app.contacts().createContact(new ContactsData().withFirstName("FirstName-" + unicNumber).withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru"));

        int before = app.db().connectionsNumber();
        Contacts beforeDB = app.db().contacts();
        System.out.println("ДО" + beforeDB);

        app.goTo().contactPage();
        app.contacts().addUnicContactToUnicGroup(unicNumber);
        int after = app.db().connectionsNumber();
        Contacts afterDB = app.db().contacts();
        System.out.println("ПОСЛЕ" + afterDB);
        assertThat(after, equalTo(before + 1));
    }


}