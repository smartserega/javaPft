package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {


    @Test
    public void addContactToGroupTests() throws Exception {
        int unicNumber = app.contacts().randomNumber();
        app.db().set();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("TestAddContactGroup-" + unicNumber));

        app.goTo().contactPage();
        app.contacts().createContact(new ContactsData().withFirstName("FirstName-" + unicNumber).withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru"));


        System.out.println("ДО" + app.db().connectionContacts());
        int after = app.db().connectionContacts().size();


        app.goTo().contactPage();
        app.contacts().addUnicContactToUnicGroup(unicNumber);
        System.out.println("ПОСЛЕ" + app.db().connectionContacts());

        int before = app.db().connectionContacts().size();
        assertThat(after, equalTo(before+1));


        app.goTo().contactPage();
        app.contacts().deleteUnicContact(unicNumber);
        app.goTo().groupPage();
        app.group().deleteUnicGroup(unicNumber);
    }


}