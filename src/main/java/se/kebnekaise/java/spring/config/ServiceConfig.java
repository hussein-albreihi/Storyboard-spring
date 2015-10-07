package se.kebnekaise.java.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.kebnekaise.java.spring.service.IssueService;
import se.kebnekaise.java.spring.service.TeamService;
import se.kebnekaise.java.spring.service.UserService;
import se.kebnekaise.java.spring.service.WorkItemService;

@Configuration
public class ServiceConfig
{
	@Bean
	public UserService userService() {
		return new UserService();
	}
	@Bean
	public TeamService teamService() {
		return new TeamService();
	}

	@Bean
	public WorkItemService workItemService(){
		return new WorkItemService();
	}

	@Bean
	public IssueService issueService(){
		return new IssueService();
	}
}
