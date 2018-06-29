package ru.stqa.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactsData;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactsData contactsData, boolean creation) {
        type(By.name("firstname"), contactsData.getTest_first_name());
        type(By.name("middlename"), contactsData.getTest_middle_name());
        type(By.name("lastname"), contactsData.getTest_last_name());
        type(By.name("nickname"), contactsData.getTest_nickname());
        type(By.name("title"), contactsData.getTest_title());
        type(By.name("company"), contactsData.getTest_compane());
        type(By.name("address"), contactsData.getTest_address());
        type(By.name("mobile"), contactsData.getMobile());
        type(By.name("email"), contactsData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initAddNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteContact() {
        click(By.xpath(".//div[@id='content']/form[2]/input[2]"));
    }

    public void createContact(ContactsData contact) {
        initAddNewContact();
        fillContactData(contact, true);
        initContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactsCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void editContact() {
        click(By.xpath(".//*[@id=\"maintable\"]/tbody/tr[2]/td[8]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returntoContactPage() {
        click(By.linkText("home"));
    }


    public List<ContactsData> getContactList() {
        List<ContactsData> contacts = new ArrayList<ContactsData>();
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody//td[2]"));
        for (WebElement element: elements){
            String name = element.getText();
            ContactsData contact = new ContactsData(name, "Test Middle name", "Test Last Name", "Test Nickname", "Test Title", "Test Compane", "Test Address", "+749511111111", "+790511111111", "E-mail@E-mail.ru", "Test1");
            contacts.add(contact);
        }
        return contacts;
    }
}






