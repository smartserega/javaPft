package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactsData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {


    @BeforeMethod
    public void ensurePrecondtions() {
        app.goTo().contactPage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactsData().withFirstName("FirstName").withMiddleName("MiddleName").
                    withLastName("LastName").withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("111").withHomePhone("222").withEmail("E-mail@E-mail.ru").withWorkPhone("333").
                    withGroup("Test1"));
        }
    }


    @Test
    public void testContactsPhones() {
        app.goTo().contactPage();
        ContactsData contact = app.contacts().all().iterator().next();
        ContactsData contactInfoFromEditForm = app.contacts().InfoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones(ContactsData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
