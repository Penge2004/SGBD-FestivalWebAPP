package com.project.sgbd_project.Repository;

import com.project.sgbd_project.Domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    @Query("SELECT a FROM Artist a ORDER BY a.artist_id ASC")
    List<Artist> findAll();
}
