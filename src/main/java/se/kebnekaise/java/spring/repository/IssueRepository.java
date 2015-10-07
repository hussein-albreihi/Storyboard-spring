package se.kebnekaise.java.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import se.kebnekaise.java.spring.model.Issue;
import se.kebnekaise.java.spring.model.WorkItem;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {

    List<Issue> findByWorkItem(WorkItem workItem);

    Issue findByTitle(String title);
}
