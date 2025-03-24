package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Integer> {

    @Query("SELECT s FROM Stage s ORDER BY s.stage_id asc ")
    List<Stage> findAll();
}
