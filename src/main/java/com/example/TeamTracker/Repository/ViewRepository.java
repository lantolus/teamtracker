package com.example.TeamTracker.Repository;

import com.example.TeamTracker.Model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

//Setting this class as a Central Repo for View Objects
@Repository
@Transactional
//Extending JPA Repo so we can use default methods
public interface ViewRepository extends JpaRepository<View, Long> {

    Collection<View> findViewByOwner(String owner);
    Collection<View> findViewByIdAndOwner(Long id, String owner);

}
