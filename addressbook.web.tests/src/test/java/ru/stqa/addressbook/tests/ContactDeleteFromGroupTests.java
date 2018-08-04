package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("test 1");
            app.group().create(newGroup);
        }
        Groups groups = app.db().groups();
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                    inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void deleteContactFromGroupTests() {
        Contacts allContacts = app.db().contacts();

        ContactsData contactForDelete = app.contacts().findAnyContact(allContacts);
        Groups allgroups = app.db().groups();
        checkNotEmptyGroupPresent(allgroups, contactForDelete);
        GroupData notEmptyGroup = findGroupforDeleteContact(contactForDelete);

        ContactsData updatedContact1 = contactForDelete.inGroup(notEmptyGroup);
        Groups beforDeletionContactFromGroup = updatedContact1.getGroups();

        app.goTo().contactPage();
        app.contacts().deleteContacFromGroup(contactForDelete.getId(), notEmptyGroup.getId());

        ContactsData updatedContact = contactForDelete.inGroup(notEmptyGroup);
        Groups afterDelitionContactToGroup = updatedContact.getGroups();

        assertThat(afterDelitionContactToGroup, equalTo(beforDeletionContactFromGroup.withAdded(notEmptyGroup)));
    }

    private GroupData findGroupforDeleteContact(ContactsData contactForAdd) {
        Groups groups = app.db().groups();
        GroupData notEmptyGroup = null;
        for (GroupData group : groups) {
            if (group.getContacts().contains(contactForAdd)) {
                notEmptyGroup = group;
            }
        }
        return notEmptyGroup;
    }

    private void checkNotEmptyGroupPresent(Groups allgroups, ContactsData contactForDelete) {
        if (contactForDelete.getGroups().size() != allgroups.size()) {
            checkCreateGroup(allgroups, contactForDelete);
            GroupData emptyGroup = findGroupforContact(contactForDelete);
            app.goTo().contactPage();
            app.contacts().addContactToUnicGroup(contactForDelete.getId(), emptyGroup.getId());
        }
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
