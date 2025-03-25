package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The JPA repository for the User object
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * This is used to display the data in sorted order
     * */
    @Override
    @Query("SELECT u FROM User u ORDER BY u.userid ASC")
    List<User> findAll();
}
