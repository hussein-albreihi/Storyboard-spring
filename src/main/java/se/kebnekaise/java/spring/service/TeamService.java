package se.kebnekaise.java.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.kebnekaise.java.spring.model.Team;
import se.kebnekaise.java.spring.model.User;
import se.kebnekaise.java.spring.repository.TeamRepository;
import se.kebnekaise.java.spring.repository.UserRepository;

import java.util.List;

public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(String teamName, Team team) {
        Team teamFromDatabase = teamRepository.findByTeamName(teamName);
        teamFromDatabase.setTeamName(team.getTeamName());
        return teamRepository.save(teamFromDatabase);
    }

    public void deleteTeam(Team teamToBeDeleted) {
        List<User> x = userRepository.findUserByTeam(teamToBeDeleted);
        for (User d : x) {
            d.setTeam(null);
            userRepository.save(d);
        }
        teamRepository.delete(teamToBeDeleted);
    }

    public List<User> getAllUsersInTeam(String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        return userRepository.findUserByTeam(team);
    }

    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeambyName(String name) {
        return teamRepository.findByTeamName(name);
    }

    public User addUserToTeam(String teamname, User user) {
        Team fetchedTeam = teamRepository.findByTeamName(teamname);
        User fetchedUser = userRepository.findUserByUsername(user.getUsername());
        fetchedUser.setTeam(fetchedTeam);
        return userRepository.save(fetchedUser);
    }

}
