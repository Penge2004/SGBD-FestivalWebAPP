package com.project.sgbd_project.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stages")  // Explicitly setting table name
@NoArgsConstructor
@AllArgsConstructor
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stage_id;

    private String name;
    private String location;

    public int getStage_id() {
        return stage_id;
    }

    public void setStage_id(int stage_id) {
        this.stage_id = stage_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "stage_id=" + stage_id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
