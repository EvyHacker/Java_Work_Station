package usa.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import usa.stqa.pft.mantis.model.Issue;
import usa.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager aap) {
        this.app = app;
    }

    public Set<Project> getProject() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");

        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue())
                .withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(
                new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));//transfer to conf.file
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("web.adminLogin", "web.adminPassword", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add("web.adminLogin", "web.adminPassword", issueData);
        IssueData createdIssueData = mc.mc_issue_get("web.adminLogin", "web.adminPassword", issueId);
        return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                        .withName(createdIssueData.getProject().getName()));
    }

    public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        String status = issueData.getStatus().getName();
        return status;
    }
//    private String username() {
//        return app.getProperty("web.adminLogin");
//    }
//
//    private String password() {
//        return app.getProperty("web.adminPassword");
//    }

}
