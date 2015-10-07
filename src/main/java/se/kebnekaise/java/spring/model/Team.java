package se.kebnekaise.java.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Team extends AbstractEntity {

    private String teamName;

    @OneToMany(mappedBy = "team")
    private Collection<User> users;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private Collection<WorkItem> workItems;

    protected Team() {
    }

    public Team(String name) {
        this.teamName = name;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addWorkItem(WorkItem workItem) {
        workItems.add(workItem);
    }

    public String getTeamName() {
        return teamName;
    }
}
