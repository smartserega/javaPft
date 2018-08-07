package tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.mantis.model.Issue;
import ru.stqa.pft.rest.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {


    @Test
    public void testState() throws MalformedURLException, ServiceException, RemoteException {
        int isueID = 0000002;
        System.out.println(isIssueOpen(isueID));
        skipIfNotFixed(isueID);
    }


    @Test(enabled = false)
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test(enabled = false)
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test Issue").withDescriotion("Test IssueDescription").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

}
