package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Artist;
import com.project.sgbd_project.Domain.User;
import com.project.sgbd_project.Services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The Controller to the Artist object.
 * It is the connection between the UI (website) and the Service layer.
 * It makes the mapping with the different HTTP requests
 * */
@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public Optional<Artist> getArtistById(@PathVariable int id) {
        return artistService.getArtistById(id);
    }

    /**
     * This makes the Create operation
     * */
    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.saveArtist(artist);
    }

    /**
     * This makes the Delete operation
     * */
    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable int id) {
        artistService.deleteArtist(id);
    }

    /**
     * This makes the Update operation
     * */
    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable int id, @RequestBody Artist updatedArtist) {
        return artistService.updateArtist(id, updatedArtist);
    }
}
