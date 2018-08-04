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
        if (app.db().contacts().size() == 0) {
            app.contacts().createContact(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
    }


    @Test
    public void addContactToGroupTests() {
        Groups allgroups = app.db().groups();
        Contacts allContacts = app.db().contacts();

        ContactsData contactForAdd = app.contacts().findAnyContact(allContacts);
        Groups beforAdditionContactToGroup = contactForAdd.getGroups();
        checkCreateGroup(allgroups, contactForAdd);
        GroupData emptyGroup = findGroupforContact(contactForAdd);

        app.goTo().contactPage();
        app.contacts().addContactToUnicGroup(contactForAdd.getId(), emptyGroup.getId());

        ContactsData updatedContact = contactForAdd.inGroup(emptyGroup);
        Groups afterAdditionContactToGroup = updatedContact.getGroups();

        assertThat(afterAdditionContactToGroup, equalTo(beforAdditionContactToGroup.withAdded(emptyGroup)));
    }


    private void checkCreateGroup(Groups allgroups, ContactsData contactForAdd) {
        if (contactForAdd.getGroups().size() == allgroups.size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
    }


    private GroupData findGroupforContact(ContactsData contactForAdd) {
        Groups groups = app.db().groups();
        GroupData emptyGroup = null;
        for (GroupData group : groups) {
            if (!group.getContacts().contains(contactForAdd)) {
                emptyGroup = group;
            }
        }
        return emptyGroup;
    }


}