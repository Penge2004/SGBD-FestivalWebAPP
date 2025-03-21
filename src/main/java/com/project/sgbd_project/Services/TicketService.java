package com.project.sgbd_project.Services;

import com.project.sgbd_project.Domain.Ticket;

import com.project.sgbd_project.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(int id) {
        return ticketRepository.findById(id);
    }

    public Ticket saveTicket(Ticket ticket) {
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
