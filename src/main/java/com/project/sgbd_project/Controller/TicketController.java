package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.*;
import com.project.sgbd_project.Domain.Ticket;
import com.project.sgbd_project.Repository.PerformanceRepository;
import com.project.sgbd_project.Repository.TicketRepository;
import com.project.sgbd_project.Repository.UserRepository;
import com.project.sgbd_project.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The Controller to the Ticket object.
 * It is the connection between the UI (website) and the Service layer.
 * It makes the mapping with the different HTTP requests
 * */
@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable int id) {
        return ticketService.getTicketById(id);
    }

    /**
     * This makes the Create operation
     * */
    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequest ticketRequest) {

        System.out.println(ticketRequest);

        return ticketService.saveTicket(Integer.parseInt(ticketRequest.getUserid()),
                                        Integer.parseInt(ticketRequest.getPerformance_id()),
                                        ticketRequest.getPrice(),
                                        ticketRequest.getTicket_type());
    }

    /**
     * This makes the Delete operation
     * */
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
    }

    /**
     * This makes the Update operation
     * */
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable int id, @RequestBody TicketRequest ticketRequest) {

        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        int userId = Integer.parseInt(ticketRequest.getUserid());
        User newUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int performanceID = Integer.parseInt(ticketRequest.getPerformance_id());
        Performance newPerformance = performanceRepository.findById(performanceID)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        existingTicket.setUser(newUser);
        existingTicket.setPerformance(newPerformance);
        existingTicket.setPrice(ticketRequest.getPrice());
        existingTicket.setTicket_type(ticketRequest.getTicket_type());

        return ticketRepository.save(existingTicket);
    }
}
