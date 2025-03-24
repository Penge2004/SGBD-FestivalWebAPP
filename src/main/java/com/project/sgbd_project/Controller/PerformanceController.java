package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Artist;
import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;


    @GetMapping("/{id}")
    public Optional<Performance> getPerformanceById(@PathVariable int id) {
        return performanceService.getPerformanceById(id);
    }

    @PostMapping
    public Performance createPerformance(@RequestBody Performance performance) {
        return performanceService.savePerformance(performance);
    }

    @DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable int id) {
        performanceService.deletePerformance(id);
    }

    @PutMapping("/{id}")
    public Performance updatePerformance(@PathVariable int id, @RequestBody Performance updatedPerformance) {
        return performanceService.updatePerformance(id, updatedPerformance);
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
