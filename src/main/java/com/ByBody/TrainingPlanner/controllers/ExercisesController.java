package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.Exercise;
import com.ByBody.TrainingPlanner.repo.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;

@Controller
public class ExercisesController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/exercises")
    public String exercisesMain(Model model){
        Iterable<Exercise> exercises = exerciseRepository.findAll();
        model.addAttribute("exercises", exercises );
        model.addAttribute("title", "Упражнения");
        return "exercises";
    }

    @GetMapping("/exercises/add")
    public String exercisesAdd(Model model){
        model.addAttribute("title", "Добавление упражнения");
        return "exercises-add";
    }

    @PostMapping("/exercises/add")
    public String exercisesPostAdd(@RequestParam String title,
                                   @RequestParam String anons,
                                   @RequestParam String howToDoIt,
                                   @RequestParam String howNotToDoIt,
                                   @RequestParam String advantages,
                                   @RequestParam String contraindications,
                                   @RequestParam String complication,
                                   @RequestParam String mainPhotoPath,
                                   @RequestParam String mainVideoPath){
        Exercise exercise = new Exercise(title, anons, howToDoIt, howNotToDoIt, advantages, contraindications, complication, mainPhotoPath, mainVideoPath);
        exerciseRepository.save(exercise);
        return "redirect:/exercises";
    }
}
