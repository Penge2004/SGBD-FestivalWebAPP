package com.project.sgbd_project.Domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * The User Object, it could also be named viewer, it corresponds with the User in the Database
 * */
@Entity
@Table(name = "users")  // Ensures correct table name
@NoArgsConstructor
@AllArgsConstructor

public class User {

    /**
     * The Primary key
     * It is names userid because user_id is a function in PostgreSQL
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userid;

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
