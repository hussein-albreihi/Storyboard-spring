package se.kebnekaise.java.spring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Issue extends AbstractEntity {

    String title;
    String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private WorkItem workItem;

    protected Issue() {
    }

    public Issue(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Issue addWorkItem(WorkItem item) {
        this.workItem = item;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
