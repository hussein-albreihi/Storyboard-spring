package se.kebnekaise.java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.kebnekaise.java.spring.model.Issue;
import se.kebnekaise.java.spring.model.WorkItem;
import se.kebnekaise.java.spring.repository.IssueRepository;

import java.util.List;

public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public Issue createNewIssue(Issue issue) {
        try {
            Issue newIssue = new Issue(issue.getTitle(), issue.getDescription());
            if (!newIssue.getTitle().isEmpty() && !newIssue.getDescription().isEmpty()) {
                return issueRepository.save(newIssue);
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    public Issue updateIssue(Long id, Issue issue) {
        Issue fromDatabase = issueRepository.findOne(id);
        fromDatabase.setTitle(issue.getTitle());
        fromDatabase.setDescription(issue.getDescription());
        return issueRepository.save(fromDatabase);
    }

    public List<Issue> findAllWorkItemWithIssue(WorkItem workItem) {
        return issueRepository.findByWorkItem(workItem);
    }

    public Iterable getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue findIssueByTitle(String title) {
        return issueRepository.findByTitle(title);
    }
}
