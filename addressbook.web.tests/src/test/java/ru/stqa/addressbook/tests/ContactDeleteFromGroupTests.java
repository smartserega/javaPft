package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase{

    @Test
    public void deleteContactFromGroupTests() throws Exception {
        int unicNumber = app.contacts().randomNumber();

        app.goTo().groupPage();
        app.group().create(new GroupData().withName("TestAddContactGroup-" + unicNumber));
        app.goTo().contactPage();
        app.contacts().createContact(new ContactsData().withFirstName("FirstName-" + unicNumber).withMiddleName("MiddleName").
                withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru"));

        app.goTo().contactPage();
        app.contacts().addUnicContactToUnicGroup(unicNumber);
        int before = app.db().connectionsNumber();
        app.goTo().contactPage();
        app.contacts().deleteUnicContacFromUnicGroup(unicNumber);
        int after = app.db().connectionsNumber();
        assertThat(after, equalTo(before-1));

}


}

