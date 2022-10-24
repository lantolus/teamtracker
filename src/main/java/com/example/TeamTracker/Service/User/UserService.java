package com.example.TeamTracker.Service.User;

import com.example.TeamTracker.Model.Role;
import com.example.TeamTracker.Model.User;

import java.util.List;

public interface UserService {

    //Abstract User related methods
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void deleteUserById(Long id);
}
