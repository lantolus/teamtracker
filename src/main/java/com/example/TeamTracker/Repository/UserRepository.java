package com.example.TeamTracker.Repository;

import com.example.TeamTracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Setting this class as a Central Repo for User Objects
@Repository
//Extending JPA Repo so we can use default methods
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    void deleteById(Long id);
}
