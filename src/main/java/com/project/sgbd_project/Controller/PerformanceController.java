package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Artist;
import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Domain.PerformanceRequest;
import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Repository.ArtistRepository;
import com.project.sgbd_project.Repository.PerformanceRepository;
import com.project.sgbd_project.Repository.StageRepository;
import com.project.sgbd_project.Services.PerformanceService;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.*;


/**
 * The Controller to the Performance object.
 * It is the connection between the UI (website) and the Service layer.
 * It makes the mapping with the different HTTP requests
 * */
@RestController
@RequestMapping("/performances")
public class PerformanceController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PerformanceService performanceService;
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private StageRepository stageRepository;


    @GetMapping("/{id}")
    public Optional<Performance> getPerformanceById(@PathVariable int id) {
        return performanceService.getPerformanceById(id);
    }

    /**
     * This makes the Create operation
     * */
    @PostMapping
    public Performance createPerformance(@RequestBody PerformanceRequest performanceRequest) {

        if(performanceRequest.getPerformanceDate() == null)
            throw new IllegalArgumentException("Date cannot be null");

        LocalDateTime performanceDateTime = LocalDateTime.parse(performanceRequest.getPerformanceDate());
        try {
            int artistID = Integer.parseInt(performanceRequest.getArtistId());
            System.out.println(artistID);
        }catch (NumberFormatException e) {
            System.out.println("There is a problem with parsing the artistID");
        }
        try {
            int stageID = Integer.parseInt(performanceRequest.getStageId());
            System.out.println(stageID);
        }catch (NumberFormatException e) {
            System.out.println("There is a problem with parsing the stageID");
        }
        return performanceService.savePerformance(Integer.parseInt(performanceRequest.getArtistId()),
                Integer.parseInt(performanceRequest.getStageId()),
                performanceDateTime);
    }

    /**
     * This makes the Delete operation
     * */
    @DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable int id) {
        performanceService.deletePerformance(id);
    }

    /**
     * This makes the Update operation
     * */
    @Transactional
    @PutMapping("/{id}")
    public Performance updatePerformance(@PathVariable int id,
                                         @RequestBody PerformanceRequest performanceRequest) {


        Performance existingPerformance = performanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance not found"));

        // Find the existing artist by the new artistId
        int artistId = Integer.parseInt(performanceRequest.getArtistId());
        Artist newArtist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        // Find the existing stage by stageId (if you want to update stage as well)
        int stageId = Integer.parseInt(performanceRequest.getStageId());
        Stage newStage = stageRepository.findById(stageId)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        System.out.println("Existing Performance: " + existingPerformance);
        System.out.println("New Artist ID: " + artistId);
        System.out.println("New Stage ID: " + stageId);
        System.out.println("New Performance Date: " + performanceRequest.getPerformanceDate());

        existingPerformance.setArtist(newArtist);
        existingPerformance.setStage(newStage);
        existingPerformance.setStart_time(LocalDateTime.parse(performanceRequest.getPerformanceDate()));

        // Force Hibernate to update the database
        entityManager.flush();
        entityManager.refresh(existingPerformance);

        System.out.println("Final Updated Performance: " + existingPerformance);

        return existingPerformance;
    }

    @GetMapping
    public List<Map<String, Object>> getAllPerformances() {
        List<Performance> performances = performanceService.getAllPerformances();

        List<Map<String, Object>> formattedPerformances = new ArrayList<>();
        for (Performance p : performances) {
            Map<String, Object> performanceData = new HashMap<>();
            performanceData.put("performance_id", p.getPerformance_id());
            performanceData.put("artist_id", p.getArtist() != null ? p.getArtist().getArtist_id() : null);
            performanceData.put("stage_id", p.getStage() != null ? p.getStage().getStage_id() : null);
            performanceData.put("start_time", p.getStart_time() != null ? p.getStart_time() : "TBD");

            formattedPerformances.add(performanceData);
        }

        return formattedPerformances;
    }
}
