package se.kebnekaise.java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import se.kebnekaise.java.spring.model.Team;
import se.kebnekaise.java.spring.model.User;
import se.kebnekaise.java.spring.model.WorkItem;
import se.kebnekaise.java.spring.repository.UserRepository;
import se.kebnekaise.java.spring.repository.WorkItemRepository;

import java.util.List;

public final class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkItemRepository workItemRepository;

    public User createNewUser(User user) {
        try {
            User createdUser = new User(user.getFirstname(), user.getSurname(), user.getUsername());
            return userRepository.save(createdUser);
        } catch (NullPointerException | DataAccessException e) {
            return null;
        }
    }

    public User updateUser(String username, User user) {
        try {
            User userFromDB = userRepository.findByUsername(username);
            if (user.getUsername() != null && user.getSurname() != null && user.getFirstname() != null) {
                userFromDB.setFirstname(user.getFirstname());
                userFromDB.setSurname(user.getSurname());
                userFromDB.setUsername(user.getUsername());
                return userRepository.save(userFromDB);
            }
        } catch (NullPointerException | DataAccessException e) {
            return null;
        }
        return null;
    }

    public void deleteUser(User user) {
        try {
            List<WorkItem> workitems = workItemRepository.findByUser(user);
            workitems.forEach(v -> v.setUser(null));
            workItemRepository.save(workitems);
            userRepository.delete(user);
        } catch (NullPointerException e) {

        }
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findAllUsersInTeam(Team team) {
        return userRepository.findUserByTeam(team);
    }

    public List<WorkItem> findAllWorkItemsForUser(User user) {
        return null;
    }

    public User findUserByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    public User findUserBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    public User findUserByIdentity(String identity) {
        return userRepository.findByIdentity(identity);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addWorkItemToUser(String username, Long itemId) {
        try {
            User user = userRepository.findUserByUsername(username);
            WorkItem workItem = workItemRepository.findWorkItemById(itemId);
            if (workItem != null && user != null) {
                user.addWorkItem(workItem);
                workItem.setUser(user);
                workItemRepository.save(workItem);
                userRepository.save(user);
                return user;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }
}
