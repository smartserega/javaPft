package ru.stqa.addressbook.appManager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    FirefoxDriver wd;


    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactsHelper contactsHelper;


    public void init() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactsHelper = new ContactsHelper(wd);

    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactsHelper getContactsHelper() {
        return contactsHelper;
    }
}
