package com.project.sgbd_project.Controller;

import com.project.sgbd_project.Domain.Stage;
import com.project.sgbd_project.Services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public Stage createStage(@RequestBody Stage stage) {
        return stageService.saveStage(stage);
    }

    @DeleteMapping("/{id}")
    public void deleteStage(@PathVariable int id) {
        stageService.deleteStage(id);
    }
}
