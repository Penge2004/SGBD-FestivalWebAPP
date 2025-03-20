package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    @Query("SELECT u FROM User u ORDER BY u.userid ASC")
    List<User> findAll();
}
