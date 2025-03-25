package com.project.sgbd_project.Services;

import com.project.sgbd_project.Domain.Artist;
import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Errors.ValueNotFoundException;
import com.project.sgbd_project.Repository.ArtistRepository;
import com.project.sgbd_project.Repository.PerformanceRepository;
import com.project.sgbd_project.Repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * This is the service layer for the Performance object.
 * It handles the main business logic
 * It has a direct connection with the appropriate repository
 * */
@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private StageRepository stageRepository;

    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    public Optional<Performance> getPerformanceById(int id) {
        return performanceRepository.findById(id);
    }

    public Performance savePerformance(int artist_id, int stage_id, LocalDateTime start_date) {
        // Fetch the Artist object from the database
        Artist artist = artistRepository.findById(artist_id)
                .orElseThrow(() -> new IllegalArgumentException("Artist not found"));

        // Fetch the Stage object from the database
        Stage stage = stageRepository.findById(stage_id)
                .orElseThrow(() -> new IllegalArgumentException("Stage not found"));

        Performance performance = new Performance();
        performance.setArtist(artist);  // Set the full Artist object
        performance.setStage(stage);    // Set the full Stage object
        performance.setStart_time(start_date);

        // Save the Performance
        return performanceRepository.save(performance);
    }

    public void deletePerformance(int id) {
        performanceRepository.deleteById(id);
    }

    /**
     *A better way is implemented in the Controller layer directly
     *  */

//    public Performance updatePerformance(int id, int artistId, int stageId, LocalDateTime performanceDateTime) {
//        Optional<Performance> existingPerformance = performanceRepository.findById(id);
//        if (existingPerformance.isPresent()) {
//            Performance performance = existingPerformance.get();
//            performance.setArtistId(artistId);
//            performance.setStageId(stageId);
//            performance.setStart_time(performanceDateTime);
//            return performanceRepository.save(performance);
//        } else {
//            throw new ValueNotFoundException("Performance not found with id: " + id);
//        }
//    }
}
