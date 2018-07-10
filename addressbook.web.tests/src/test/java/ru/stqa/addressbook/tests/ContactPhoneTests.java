package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactsPhones() {
        app.goTo().contactPage();
        ContactsData contact = app.contacts().all().iterator().next();
        ContactsData contactInfoFromEditForm = app.contacts().InfoFromEditForm(contact);
        assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
        assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }


    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
