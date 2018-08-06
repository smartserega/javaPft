package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePasswordByAdmin() throws IOException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        app.changePasswordHelper().adminLogin("administrator", "root1");
        app.changePasswordHelper().userSettings();
        app.changePasswordHelper().selectUser();
        String newUserEmail = app.changePasswordHelper().getEmail();
        String newUserName = app.changePasswordHelper().getName();
        app.changePasswordHelper().resetUserPassword();
        String newPassword = "newPass";
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 18000);
        String confirmationLink = findConfirmationLink(mailMessages, newUserEmail);
        app.registration().resetPassword(confirmationLink, newPassword);
        HttpSession session = app.newSession();
        session.login(newUserName, newPassword);
        assertTrue(session.login(newUserName, newPassword));
        assertTrue(session.isLoggedInAs(newUserName));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}