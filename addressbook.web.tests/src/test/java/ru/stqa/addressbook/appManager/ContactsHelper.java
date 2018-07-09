package ru.stqa.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactsData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void create(ContactsData contact) {
        initAddNewContact();
        fillContactData(contact, true);
        initContactCreation();
    }

    public void modifyContact(int index, ContactsData contacts) {
        editContact(index);
        fillContactData(contacts, false);
        submitContactModification();
        returntoContactPage();
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

    public void editContact(int index) {
        wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[*]/td[8]/a/img")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returntoContactPage() {
        click(By.linkText("home"));
    }

    public void delete(int index) {
        selectContact(index);
        editContact(index);
        deleteContact();
        returntoContactPage();
    }


    public List<ContactsData> list() {
        List<ContactsData> contacts = new ArrayList<ContactsData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactsData().withId(id).withFirstName(firstName).withMiddleName("MiddleName").
                    withLastName(lastName).withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                    withGroup("Test1"));

        }
        return contacts;
    }


    public Set<ContactsData> all() {
        Set<ContactsData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactsData().withId(id).withFirstName(firstName).withMiddleName("MiddleName").
                    withLastName(lastName).withNickname("nickname").withTitle("Title").withCompane("company").
                    withAddress("address").withMobile("+79991119999").withHomePhone("+79991119999").withEmail("E-mail@E-mail.ru").
                    withGroup("Test1"));

        }
        return contacts;
    }

}






