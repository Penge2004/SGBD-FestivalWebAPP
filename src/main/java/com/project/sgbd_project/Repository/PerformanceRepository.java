package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
}
