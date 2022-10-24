package com.example.TeamTracker.Service.User;

import com.example.TeamTracker.Model.Role;
import com.example.TeamTracker.Model.User;
import com.example.TeamTracker.Repository.RoleRepository;
import com.example.TeamTracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    //Injecting fields
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override //Overriding method to get whole User object /w all  attrib.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override  // Overrided method for creating New User Objects and saving them to the DB
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());//Console log for User saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));//Encoding User password ,so its not vulnerable plain text
        return userRepository.save(user);//Saving User to DB
    }


    @Override  // Method for saving roles to the Roles table
    public Role saveRole(Role role) {
        log.info("Saving new role {}to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override  //Method for apllying Roles to Users
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {} database", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override // Method for Fetching one User by UN
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override // Method for fetching All Users
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    // Method for deleting User objects based on the ID attr.
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}



