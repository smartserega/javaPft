package ru.stqa.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.ContactsData;

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
        click(By.xpath(".//*[@id=\"content\"]/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
    }

    public void create(ContactsData contact) {
        initAddNewContact();
        fillContactData(contact, true);
        initContactCreation();
    }

    public void modifyContact(ContactsData contacts) {
        editContactByid(contacts.getId());
        fillContactData(contacts, false);
        submitContactModification();
        returntoContactPage();
    }


    public int getContactsCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    private void editContactByid(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }


    public void submitContactModification() {
        click(By.name("update"));
    }

    public void returntoContactPage() {
        click(By.linkText("home"));
    }

    public void delete(ContactsData contact) {
        selectContactById(contact.getId());
        deleteContact();
        returntoContactPage();
    }


    public Contacts all() {
        Contacts contacts = new Contacts();
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






