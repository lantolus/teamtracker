package com.example.TeamTracker.Repository;

import com.example.TeamTracker.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

//Setting this class as a Central Repo for Employee Objects
//Extending JPA Repo so we can use default methods
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Custom method for listing Roles by name
    Role findByName(String name);
}
