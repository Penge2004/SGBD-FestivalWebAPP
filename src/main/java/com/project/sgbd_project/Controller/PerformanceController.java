package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Artist;
import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Domain.PerformanceRequest;
import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Repository.ArtistRepository;
import com.project.sgbd_project.Repository.PerformanceRepository;
import com.project.sgbd_project.Repository.StageRepository;
import com.project.sgbd_project.Services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

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

    @PostMapping
    public Performance createPerformance(@RequestBody PerformanceRequest performanceRequest) {

        System.out.println(performanceRequest);

        System.out.println("Received performance request: " + performanceRequest.getPerformanceDate());
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

    @DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable int id) {
        performanceService.deletePerformance(id);
    }

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

        // Update the performance with the new artist and stage
        existingPerformance.setArtist(newArtist);  // Set the new artist
        existingPerformance.setStage(newStage);    // Set the new stage (optional)
        existingPerformance.setStart_time(LocalDateTime.parse(performanceRequest.getPerformanceDate()));  // Update performance time

        // Save the updated performance entity
        return performanceRepository.save(existingPerformance);
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
