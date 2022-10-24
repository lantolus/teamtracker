package com.example.TeamTracker;

import com.example.TeamTracker.Controller.EmployeeController;
import com.example.TeamTracker.Controller.UserController;
import com.example.TeamTracker.Controller.ViewController;
import com.example.TeamTracker.Model.Role;
import com.example.TeamTracker.Model.User;
import com.example.TeamTracker.Model.View;
import com.example.TeamTracker.Service.Employee.EmployeeServiceImpl;
import com.example.TeamTracker.Service.FeView.FEViewColumnsImplementation;
import com.example.TeamTracker.Service.User.UserServiceImpl;
import com.example.TeamTracker.Service.View.ViewServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

//Disabling Spring automatic SecurityConfiguration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//Scanning annotations, so Spring App is able to find Methods within all classes in need
@ComponentScan(basePackageClasses = EmployeeController.class)
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = EmployeeServiceImpl.class)
@ComponentScan(basePackageClasses = ViewController.class)
@ComponentScan(basePackageClasses = UserServiceImpl.class)
@ComponentScan(basePackageClasses = ViewServiceImpl.class)
@ComponentScan(basePackageClasses = FEViewColumnsImplementation.class)
@Configuration
public class TeamTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamTrackerApplication.class, args);
	}

	// Creating 2 default users, based on Deutsche Telekom Policies
	@Bean
	CommandLineRunner run(UserServiceImpl userServiceImpl, ViewServiceImpl viewServiceImpl, FEViewColumnsImplementation feViewColumnsImplementation) {
		return args -> {

			userServiceImpl.saveUser(new User(1L, "peter.denko", "Nazdravie!85", new ArrayList<>(), "peter.denko@email.com"));
			userServiceImpl.saveUser(new User(2L, "test.teamtracker", "Pozdravujem?14", new ArrayList<>(), "test.teamtracker@email.com"));
			userServiceImpl.saveUser(new User(3L, "maros.stanko@email.com", "TeamTracker!28", new ArrayList<>(), "maros.stanko@email.com"));

			userServiceImpl.saveRole(new Role(1L, "admin"));
			userServiceImpl.saveRole(new Role(2L, "user"));

			userServiceImpl.addRoleToUser("peter.denko", "admin");
			userServiceImpl.addRoleToUser("test.teamtracker", "user");
			userServiceImpl.addRoleToUser("maros.stanko@email.com", "admin");


			viewServiceImpl.createNewView(new View(1L,"favorite1","peter.denko","EMEA2,Salary,WIW,COCE,Private phone,Grade,Employment status,Calendar Type"));
			viewServiceImpl.createNewView(new View(1L,"favorite1","maros.stanko@email.com","EMEA2,Salary,WIW,COCE,Private phone,Grade,Employment status,Calendar Type"));
			feViewColumnsImplementation.initializeList();



		};
	}
}