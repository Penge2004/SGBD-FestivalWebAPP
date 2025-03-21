package com.project.sgbd_project.Services;

import com.project.sgbd_project.Domain.Performance;
import com.project.sgbd_project.Repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public List<Performance> getAllPerformances() {
        return performanceRepository.findAll();
    }

    public Optional<Performance> getPerformanceById(int id) {
        return performanceRepository.findById(id);
    }

    public Performance savePerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    public void deletePerformance(int id) {
        performanceRepository.deleteById(id);
    }

    public Performance updatePerformance(int id, Performance updatedPerformance) {
        return performanceRepository.findById(id)
                .map(existingPerformance -> {
                    existingPerformance.setArtist(updatedPerformance.getArtist());
                    existingPerformance.setStage(updatedPerformance.getStage());
                    existingPerformance.setStart_time(updatedPerformance.getStart_time());
                    return performanceRepository.save(existingPerformance);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
