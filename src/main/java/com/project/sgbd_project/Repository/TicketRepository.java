package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The JPA repository for the Ticket object
 * */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    /**
     * This is used to display the data in sorted order
     * */
    @Query("SELECT t FROM Ticket t order by t.ticket_id ASC ")
    List<Ticket> findAll();
}
