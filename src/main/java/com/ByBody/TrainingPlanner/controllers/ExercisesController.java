package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.Exercise;
import com.ByBody.TrainingPlanner.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/exercises/{id}")
    public String exercisesDetails(@PathVariable(value = "id") long id, Model model){
        if(!exerciseRepository.existsById(id)){
            return "redirect:/exercises";
        }

        Optional<Exercise> exercise = exerciseRepository.findById(id);
        ArrayList<Exercise> res = new ArrayList<>();
        exercise.ifPresent(res::add);
        model.addAttribute("exercise", res);
        return "exercise-details";
    }

    @GetMapping("/exercises/{id}/edit")
    public String exercisesEdit(@PathVariable(value = "id") long id, Model model){
        if(!exerciseRepository.existsById(id)){
            return "redirect:/exercises";
        }

        model.addAttribute("title", "Редактирование упражнения");

        Optional<Exercise> exercise = exerciseRepository.findById(id);
        ArrayList<Exercise> res = new ArrayList<>();
        exercise.ifPresent(res::add);
        model.addAttribute("exercise", res);
        return "exercise-edit";
    }

    @PostMapping("/exercises/add")
    public String exercisesAdd(@RequestParam String title,
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

    @PostMapping("/exercises/{id}/edit")
    public String exercisesUpdate(@PathVariable(value = "id") long id,
                                  @RequestParam String title,
                                   @RequestParam String anons,
                                   @RequestParam String howToDoIt,
                                   @RequestParam String howNotToDoIt,
                                   @RequestParam String advantages,
                                   @RequestParam String contraindications,
                                   @RequestParam String complication,
                                   @RequestParam String mainPhotoPath,
                                   @RequestParam String mainVideoPath){
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        exercise.setTitle(title);
        exercise.setAnons(anons);
        exercise.setAdvantages(advantages);
        exercise.setComplication(complication);
        exercise.setContraindications(contraindications);
        exercise.setHowNotToDoIt(howNotToDoIt);
        exercise.setHowToDoIt(howToDoIt);
        exercise.setMainPhotoPath(mainPhotoPath);
        exercise.setMainVideoPath(mainVideoPath);

        exerciseRepository.save(exercise);
        return "redirect:/exercises/" + id;
    }

    @PostMapping("/exercises/{id}/remove")
    public String exercisesDelete(@PathVariable(value = "id") long id, Model model){
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        exerciseRepository.delete(exercise);

        return "redirect:/exercises/";
    }
}
