package com.project.sgbd_project.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


/**
 * The Performance Object, it corresponds with the Performance in the Database
 * */
@Entity
@Table(name = "performances")  // Explicitly setting table name
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

    /**
     * The primary key
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int performance_id;

    /**
     * Foreign key to the Artist table
     * */
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = false)
    private Artist artist;

    /**
     * Foreign key to the Stage table
     * */
    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "stage_id", nullable = false)
    private Stage stage;

    @Column(name = "performance_time")
    private LocalDateTime start_time;

    public int getPerformance_id() {
        return performance_id;
    }

    public void setPerformance_id(int performance_id) {
        this.performance_id = performance_id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public int getArtistId() {
        return artist.getArtist_id();
    }

    public void setArtistId(int id){
        this.artist.setArtist_id(id);
    }

    public void setStageId(int id){
        this.stage.setStage_id(id);
    }

    public int getStageId() {
        return stage.getStage_id();
    }

    @Override
    public String toString() {
        return "Performance{" +
                "performance_id=" + performance_id +
                ", artist=" + artist +
                ", stage=" + stage +
                ", start_time=" + start_time +
                '}';
    }
}
