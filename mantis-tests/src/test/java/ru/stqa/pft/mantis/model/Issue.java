package ru.stqa.pft.mantis.model;

public class Issue {

    private int id;
    private String summary;
    private String descriotion;

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

    public String getDescriotion() {
        return descriotion;
    }

    public Issue withDescriotion(String descriotion) {
        this.descriotion = descriotion;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    private Project project;
}
