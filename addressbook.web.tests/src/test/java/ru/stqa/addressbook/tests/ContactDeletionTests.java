package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondtions() {
        Groups groups = app.db().groups();
//        GroupData group = new GroupData();
//        if (app.db().groups().size() == 0) {
//            app.group().create(group);
//        }
        app.goTo().contactPage();
        if (app.db().contacts().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                    inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void contactsDeletionTest() {
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        ContactsData deletedContact = before.iterator().next();

        app.goTo().contactPage();
        app.contacts().delete(deletedContact);
        assertThat(app.contacts().getContactsCount(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOutAdded(deletedContact)));
        verifyContactListInUi();

    }


}
