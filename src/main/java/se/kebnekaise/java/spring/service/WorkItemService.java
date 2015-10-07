package se.kebnekaise.java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import se.kebnekaise.java.spring.model.Issue;
import se.kebnekaise.java.spring.model.Team;
import se.kebnekaise.java.spring.model.User;
import se.kebnekaise.java.spring.model.WorkItem;
import se.kebnekaise.java.spring.repository.IssueRepository;
import se.kebnekaise.java.spring.repository.TeamRepository;
import se.kebnekaise.java.spring.repository.WorkItemRepository;

import java.util.ArrayList;
import java.util.List;

public class WorkItemService {

    @Autowired
    private WorkItemRepository workRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private TeamRepository teamRepository;

    public WorkItem createOrUpdateWorkItem(WorkItem work) {
        try {
            WorkItem result = new WorkItem(work.getTitle(), work.getDescription());
            if (result.getTitle().isEmpty() || result.getDescription().isEmpty()) {
                return null;
            }
            return workRepository.save(result);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<WorkItem> findByStatus(String status) {
        return workRepository.findByStatus(status);
    }

    public List<WorkItem> findWorkItemByTeam(Team team) {
        return workRepository.findByTeam(team);
    }

    public List<WorkItem> findByUser(User user) {
        return workRepository.findByUser(user);
    }

    public Slice<WorkItem> findByDescription(String searchDesc, Pageable pageable) {
        return workRepository.findByDescriptionLike(searchDesc, pageable);
    }

    public Iterable findAll() {
        return workRepository.findAll();
    }

    public WorkItem deleteWorkitem(long l) {
        try {
            WorkItem item = workRepository.findOne(l);
            workRepository.delete(l);
            return item;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public WorkItem findByTitle(String title) {
        return workRepository.findByTitle(title);
    }

    public WorkItem findById(Long id) {
        return workRepository.findWorkItemById(id);
    }

    public WorkItem updateStatusForWorkItem(Long id, String status) {
        try {
            WorkItem result = workRepository.findWorkItemById(id);
            if (result.getStatus().isEmpty()) {
                return null;
            }
            result.setStatus(status);

            return workRepository.save(result);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public List<WorkItem> findWorkItemContaining(String text) {
        return workRepository.findByDescriptionContaining(text);
    }

    public WorkItem addIssueToWorkItem(Long id, Issue issue) {
        try {
            Issue result = issueRepository.findOne(issue.getId());
            WorkItem workItem = workRepository.findWorkItemById(id);
            if (result != null && workItem != null) {
                result.addWorkItem(workItem);
                workItem.addIssue(result);
                issueRepository.save(result);
                return workRepository.save(workItem);
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
    public WorkItem addWorkitemToTeam(String name, WorkItem workItem){
        try {
            Team team = teamRepository.findByTeamName(name);
            WorkItem result = new WorkItem(workItem.getTitle(), workItem.getDescription());
            if(team != null) {
                result.setStatus(workItem.getStatus());
                result.addTeam(team);
                return workRepository.save(result);
            }
            return null;
        }catch (NullPointerException e){
            return null;
        }
    }

    public List<WorkItem> getAllWorkitemsWithAnIssue() {
        List<WorkItem> listwork = new ArrayList<>();
        for (WorkItem d : workRepository.findAll()) {
            if (!d.getIssues().isEmpty()) {
                listwork.add(d);
            }
        }
        return listwork;
    }

    public List<WorkItem> findAllIssues() {
        List<WorkItem> listwork = new ArrayList<>();
        for (WorkItem d : workRepository.findAll()) {
            if (!d.getIssues().isEmpty()) {
                listwork.add(d);
            }
        }
        return listwork;
    }
}

