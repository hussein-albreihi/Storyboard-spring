package se.kebnekaise.java.spring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import se.kebnekaise.java.spring.model.Team;
import se.kebnekaise.java.spring.model.User;
import se.kebnekaise.java.spring.model.WorkItem;

import java.util.List;


public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {

    List<WorkItem> findByStatus(String status);

    List<WorkItem> findByTeam(Team team);

    List<WorkItem> findByUser(User user);

    Slice<WorkItem> findByDescriptionLike(String searchDesc, Pageable pageable);

    WorkItem findByTitle(String title);

    WorkItem findWorkItemById(Long id);

    List<WorkItem> findByDescriptionContaining(String text);

}
