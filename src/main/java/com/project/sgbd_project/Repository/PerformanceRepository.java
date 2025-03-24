package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
    @Query("SELECT p FROM Performance p ORDER BY p.performance_id ASC")
    List<Performance> findAll();
}
