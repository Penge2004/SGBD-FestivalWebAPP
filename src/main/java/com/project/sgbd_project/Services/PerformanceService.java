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
}
