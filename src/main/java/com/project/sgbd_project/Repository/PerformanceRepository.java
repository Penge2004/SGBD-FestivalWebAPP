package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The JPA repository for the Performance object
 * */
@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {

    /**
     * This is used to display the data in sorted order
     * */
    @Query("SELECT p FROM Performance p ORDER BY p.performance_id ASC")
    List<Performance> findAll();
}
