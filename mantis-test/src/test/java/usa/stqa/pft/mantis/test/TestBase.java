package usa.stqa.pft.mantis.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import usa.stqa.pft.mantis.appmanager.ApplicationManager;
import usa.stqa.pft.mantis.model.Issue;
import usa.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class TestBase {


    protected static final ApplicationManager app
            = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

//    @AfterSuite(alwaysRun = true)
//    public void tearDown() {
//        app.stop();
//
//    }
//
//    @BeforeMethod
//    public void logTestStart(Method method, Object[] params) {
//        app.log().debug(
//                String.format(
//                        "Start `%s` with params %s",
//                        method.getName(),
//                        Arrays.asList(params)
//                )
//        );
//    }
//
//    @AfterMethod
//    public void logTestFinish(Method method) {
//        app.log().debug(String.format("Finish `%s`", method.getName()));
//    }

//    protected boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
//        return isIssueOpen(app.soap().getIssue(issueId));
//    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String status = app.soap().getIssueStatus(issueId);
        if (status.equals("resolved")) {
            return false;
        } else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


//    protected void ifNoProjectThenCreate() throws RemoteException, ServiceException, MalformedURLException {
//        if (app.soap().getProject().size() == 0) {
//            app.soap().addProject(new Project().withName("Fake Foreign System"));
//        }
//    }
}