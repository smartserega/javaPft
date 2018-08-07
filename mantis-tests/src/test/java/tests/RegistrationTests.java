package tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.rest.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testReRegistration() throws IOException, MessagingException, javax.mail.MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user@localhost%s.localdomain", now);
        String user = String.format("user%s", now);
        String password = ( "password" );
//        app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 18000);
//        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 10000);
        String confrimationLink = findConfrimationLink(mailMessages, email);
        app.registration().finish(confrimationLink, password);
        Assert.assertTrue(app.newSession().login(user, password));

    }

    private String findConfrimationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
