package ru.stqa.pft.rest.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email){

        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.xpath("//*[@id=\"signup-form\"]//input[2]"));
    }

    public void finish(String confrimationLink, String password) {
        wd.get(confrimationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[contains(text(), 'Изменить учетную запись')]"));
    }

    public void resetPassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//*[contains(text(), 'Изменить учетную запись')]"));

    }
}
