package usa.stqa.pft.mantis.model;

import biz.futureware.mantis.rpc.soap.client.ProjectData;

public class Project {
    private int id;
    private String name;
    private ProjectData projectData;

    public Project(){
        projectData = new ProjectData();
    }

    public Project (ProjectData projectData){
        this.projectData = projectData;
    }

    public ProjectData data(){
        return projectData;
    }

    public int getId() {
        return id;
    }

    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }
}
