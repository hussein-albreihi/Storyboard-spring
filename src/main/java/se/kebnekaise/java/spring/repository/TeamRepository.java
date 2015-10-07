package se.kebnekaise.java.spring.repository;

import org.springframework.data.repository.CrudRepository;
import se.kebnekaise.java.spring.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByTeamName(String name);
}
