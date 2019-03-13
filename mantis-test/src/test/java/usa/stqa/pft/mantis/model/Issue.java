package usa.stqa.pft.mantis.model;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import biz.futureware.mantis.rpc.soap.client.IssueData;

public class Issue {

    private int id;
    private String summary;
    private String description;
    private Project project;
    private IssueData issueData;
    public String status;

    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }



//    public Issue() {
//        issueData = new IssueData();
//    }
//
//    public Issue(IssueData issueData) {
//        this.issueData = issueData;
//        ObjectRef project = issueData.getProject();
//        withProject(
//                new Project()
//                        .withId(project.getId().intValue())
//                        .withName(project.getName())
//        );
//    }
//    public IssueData data() {
//        return issueData;
//    }

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public Issue withCategory(String category) {
        issueData.setCategory(category);
        return this;
    }

    public Issue withResolution(ObjectRef resolution) {
        issueData.setResolution(resolution);
        return this;
    }

    public String getCategory() {
        return issueData.getCategory();
    }

    public ObjectRef getResolution() {
        return issueData.getResolution();
    }
}
