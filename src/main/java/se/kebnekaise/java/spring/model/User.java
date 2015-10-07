package se.kebnekaise.java.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class User extends AbstractEntity {
    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String identity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Team team;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonBackReference
    Collection<WorkItem> workitems;

    protected User() {
    }

    public User(String firstname, String surname, String username) {
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.identity = UUID.randomUUID().toString();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public Team getTeam() {
        return team;
    }

    public String getIdentity() {
        return identity;
    }

    public Collection<WorkItem> getWorkitems() {
        return workitems;
    }

    public void addWorkItem(WorkItem item) {
        workitems.add(item);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}