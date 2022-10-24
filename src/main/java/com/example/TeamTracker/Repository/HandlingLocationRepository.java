package com.example.TeamTracker.Repository;

import com.example.TeamTracker.Model.HandlingLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandlingLocationRepository extends JpaRepository<HandlingLocation, Long> {
    HandlingLocation getHandlingLocationByName(String name);
}
