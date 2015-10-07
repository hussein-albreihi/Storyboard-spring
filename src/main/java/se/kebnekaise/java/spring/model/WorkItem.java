package se.kebnekaise.java.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class WorkItem extends AbstractEntity {

    private String title;
    private String description;
    private String status = "not completed";

    public WorkItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @OneToMany(mappedBy = "workItem", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    Collection<Issue> issues;

    @ManyToOne
    private Team team;

    protected WorkItem() {
    }

    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    public void setStatus(String status) {
        this.status = status;
        if(status.contains("completed")) {
        }
    }

    public void addTeam(Team team) {
        this.team = team;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public User getUser() {
        return user;
    }
}
