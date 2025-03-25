package com.project.sgbd_project.Services;

import com.project.sgbd_project.Domain.Artist;
import com.project.sgbd_project.Domain.User;
import com.project.sgbd_project.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is the service layer for the Artist object.
 * It handles the main business logic
 * It has a direct connection with the appropriate repository
 * */
@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(int id) {
        return artistRepository.findById(id);
    }

    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(int id) {
        artistRepository.deleteById(id);
    }

    public Artist updateArtist(int id, Artist updatedArtist) {
        return artistRepository.findById(id)
                .map(existingArtist -> {
                    existingArtist.setName(updatedArtist.getName());
                    existingArtist.setGenre(updatedArtist.getGenre());
                    existingArtist.setCountry(updatedArtist.getCountry());
                    return artistRepository.save(existingArtist);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
