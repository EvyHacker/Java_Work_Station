package usa.stqa.pft.mantis.test;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import usa.stqa.pft.mantis.model.Issue;
import usa.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class MantisBugSystemTest extends TestBase{

    private Issue fixedIssue;
    private Issue notfixedIssue;
    private Project project;

    @Test()
    public void testsMantisBugSystem() throws RemoteException, ServiceException, MalformedURLException {
        int issueId = 2;
        if (isIssueOpen(issueId) == false) {
            Set<Project> projects = app.soap().getProject();
            System.out.println(projects.size());
        } else {
            skipIfNotFixed(issueId);
        }
    }

//
//    @BeforeClass
//    public void preconditions() throws RemoteException, ServiceException, MalformedURLException {
//        ifNoProjectThenCreate();
//        project = app.soap().getProject().iterator().next();
//        ifNoDifferentIssuesThenCreate();
//        app.log().info("Expecting for results:  ");
//        app.log().info("testFixedFeature /successed");
//        app.log().info("testNotFixedFeature /scipped");
//    }
//
//    @Test(alwaysRun = true)
//    public void testNotFixedFeature() throws RemoteException, ServiceException, MalformedURLException {
//        skipIfNotFixed(notfixedIssue.getId());
//        app.log().error("Тест testNotFixedFeature /must be skipped!!!");
//    }
//
//    @Test(alwaysRun = true)
//    public void testFixedFeature() throws RemoteException, ServiceException, MalformedURLException {
//        skipIfNotFixed(fixedIssue.getId());
//        app.log().info("Тест testFixedFeature /works as expected");
//    }
//
//    private void ifNoDifferentIssuesThenCreate() throws RemoteException, ServiceException, MalformedURLException {
//
//        findDifferentIssues();
//
//        if (fixedIssue == null) {
//            fixedIssue = app.soap().addIssue(
//                    new Issue()
//                            .withProject(project)
//                            .withSummary("Fixed issue sample")
//                            .withDescription("Example of closed bu fixed issue")
//                            .withResolution(getFixedResolution())
//            );
//        }
//
//        if (notfixedIssue == null) {
//            notfixedIssue = app.soap().addIssue(
//                    new Issue()
//                            .withProject(project)
//                            .withSummary("Notfixed issue sample")
//                            .withDescription("Example of closed bu not fixed issue")
//                            .withResolution(getNotfixedResolution())
//            );
//        }
//
//    }
//
//    private void findDifferentIssues() throws RemoteException, ServiceException, MalformedURLException {
//        Set<Issue> issueList = app.soap().getAllIssues(project.getId());
//        for (Issue issue : issueList) {
//
//            if (fixedIssue != null && notfixedIssue != null) {
//                break;
//            }
//
//            if (isIssueOpen(issue) && notfixedIssue == null) {
//                notfixedIssue = issue;
//            } else if (!isIssueOpen(issue) && fixedIssue == null) {
//                fixedIssue = issue;
//            }
//        }
//    }
//
//    private ObjectRef getNotfixedResolution() {
//        ObjectRef resolution = new ObjectRef();
//        resolution.setId(BigInteger.valueOf(30));
//        resolution.setName("reopened");
//        return resolution;
//    }
//
//    private ObjectRef getFixedResolution() {
//        ObjectRef resolution = new ObjectRef();
//        resolution.setId(BigInteger.valueOf(20));
//        resolution.setName("fixed");
//        return resolution;
//    }
//
//
}
