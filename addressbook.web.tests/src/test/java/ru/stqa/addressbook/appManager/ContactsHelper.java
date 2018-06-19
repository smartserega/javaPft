package ru.stqa.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactsData;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactsData contactsData) {
        type("firstname", contactsData.getTest_first_name());
        type("middlename", contactsData.getTest_middle_name());
        type("lastname", contactsData.getTest_last_name());
        type("nickname", contactsData.getTest_nickname());
        type("title", contactsData.getTest_title());
        type("company", contactsData.getTest_compane());
        type("address", contactsData.getTest_address());
        type("mobile", contactsData.getMobile());
        type("email", contactsData.getEmail());
    }

    public void initAddNewContact() {
        click(By.linkText("add new"));
    }
}





