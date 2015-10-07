package se.kebnekaise.java.spring.repository;

import org.springframework.data.repository.CrudRepository;
import se.kebnekaise.java.spring.model.Team;
import se.kebnekaise.java.spring.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

    List<User> findUserByTeam(Team team);

    User findByFirstname(String firstname);

    User findBySurname(String surname);

    User findByUsername(String username);

    User findByIdentity(String identity);

}
