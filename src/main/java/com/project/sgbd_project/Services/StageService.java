package com.project.sgbd_project.Services;

import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is the service layer for the Stage object.
 * It handles the main business logic
 * It has a direct connection with the appropriate repository
 * */
@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Optional<Stage> getStageById(int id) {
        return stageRepository.findById(id);
    }

    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public void deleteStage(int id) {
        stageRepository.deleteById(id);
    }

    public Stage updateStage(int id, Stage updatedStage) {
        return stageRepository.findById(id)
                .map(existingStage -> {
                    existingStage.setName(updatedStage.getName());
                    existingStage.setLocation(updatedStage.getLocation());
                    return stageRepository.save(existingStage);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
