package com.project.sgbd_project.Services;

import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Domain.Ticket;

import com.project.sgbd_project.Domain.User;
import com.project.sgbd_project.Repository.PerformanceRepository;
import com.project.sgbd_project.Repository.TicketRepository;
import com.project.sgbd_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is the service layer for the Ticket object.
 * It handles the main business logic
 * It has a direct connection with the appropriate repository
 * */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(int id) {
        return ticketRepository.findById(id);
    }

    public Ticket saveTicket(int userid, int performance_id, double price, String ticket_type) {
        User user = userRepository.findById(userid).orElseThrow(() ->new IllegalArgumentException("User not found"));
        Performance performance = performanceRepository.findById(performance_id)
                .orElseThrow(() ->new IllegalArgumentException("Performance not found"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setPerformance(performance);
        ticket.setPrice(price);
        ticket.setTicket_type(ticket_type);

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }

    public Ticket updateTicket(int id, Ticket updatedTicket) {
        return ticketRepository.findById(id)
                .map(existingTicket -> {
                    existingTicket.setUser(updatedTicket.getUser());
                    existingTicket.setPerformance(updatedTicket.getPerformance());
                    existingTicket.setPrice(updatedTicket.getPrice());
                    existingTicket.setTicket_type(updatedTicket.getTicket_type());
                    return ticketRepository.save(existingTicket);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
