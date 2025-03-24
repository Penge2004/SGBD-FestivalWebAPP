package com.project.sgbd_project.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticket_id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "performance_id", referencedColumnName = "performance_id",nullable = false)
    private Performance performance;

    private Double price;
    private String ticket_type;


    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public User getUser() {
        return user;
    }

    @JsonProperty("userid")
    public int getUserId(){
        return user.getUserid();
    }

    @JsonProperty("performance_id")
    public int getPerformanceId(){
        return performance.getPerformance_id();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", user=" + user +
                ", performance=" + performance +
                ", price=" + price +
                ", ticket_type='" + ticket_type + '\'' +
                '}';
    }
}