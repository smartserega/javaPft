package ru.stqa.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type("group_name", groupData.getName());
        type("group_header", groupData.getHeader());
        type("group_footer", groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void selectContact() {
        click(By.xpath(".//html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
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
}
