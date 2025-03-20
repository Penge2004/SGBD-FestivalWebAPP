package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping
    public List<Performance> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

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
}
