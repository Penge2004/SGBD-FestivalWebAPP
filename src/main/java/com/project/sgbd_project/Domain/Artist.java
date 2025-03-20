package com.project.sgbd_project.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artists")  // Explicitly setting table name
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artist_id;

    private String name;
    private String genre;
    private String country;

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artist_id=" + artist_id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
