package com.project.sgbd_project.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketRequest {
    @JsonProperty("user_id")
    private String userid;

    @JsonProperty("performance_id")
    private String performance_id;

    @JsonProperty("price")
    private double price;

    @JsonProperty("type")
    private String ticket_type;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPerformance_id() {
        return performance_id;
    }

    public void setPerformance_id(String performance_id) {
        this.performance_id = performance_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
        return "TicketRequest{" +
                "userid='" + userid + '\'' +
                ", performance_id='" + performance_id + '\'' +
                ", price=" + price +
                ", ticket_type='" + ticket_type + '\'' +
                '}';
    }
}
