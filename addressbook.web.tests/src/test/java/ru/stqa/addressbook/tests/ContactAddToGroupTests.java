package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

public class ContactAddToGroupTests extends TestBase {


    @Test
    public void addContactToGroupTests() {
        int unicNumber = app.contacts().randomNumber();
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("TestAddContactGroup-" + unicNumber));

        app.goTo().contactPage();
        Groups groups = app.db().groups();
        app.contacts().create(new ContactsData().withFirstName("FirstName-" + unicNumber).withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                inGroup(groups.iterator().next()));



        app.db().connectionContacts();



        app.goTo().contactPage();
        app.contacts().addUnicContactToUnicGroup(unicNumber);

        app.db().connectionContacts();



//        app.goTo().contactPage();
//        app.contacts().deleteUnicContact(unicNumber);
//        app.goTo().groupPage();
//        app.group().deleteUnicGroup(unicNumber);
    }


}