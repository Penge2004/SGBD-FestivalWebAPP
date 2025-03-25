package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The Controller to the Stage object.
 * It is the connection between the UI (website) and the Service layer.
 * It makes the mapping with the different HTTP requests
 * */
@RestController
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    public List<Stage> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    public Optional<Stage> getStageById(@PathVariable int id) {
        return stageService.getStageById(id);
    }

    /**
     * This makes the Create operation
     * */
    @PostMapping
    public Stage createStage(@RequestBody Stage stage) {
        return stageService.saveStage(stage);
    }

    /**
     * This makes the Delete operation
     * */
    @DeleteMapping("/{id}")
    public void deleteStage(@PathVariable int id) {
        stageService.deleteStage(id);
    }

    /**
     * This makes the Update operation
     * */
    @PutMapping("/{id}")
    public Stage updateStage(@PathVariable int id, @RequestBody Stage updatedStage) {
        return stageService.updateStage(id, updatedStage);
    }
}
