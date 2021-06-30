package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.Exercise;
import com.ByBody.TrainingPlanner.repository.ExerciseRepository;
import com.ByBody.TrainingPlanner.utility.FileUploadUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ExercisesController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Value("${upload.exercises.photo.path}")
    private String exercisesPhotoPath;

    @GetMapping("/exercises")
    public String exercisesMain(Model model) {
        Iterable<Exercise> exercises = exerciseRepository.findAll();
        model.addAttribute("exercises", exercises);
        model.addAttribute("title", "Упражнения");
        return "exercises";
    }

    @GetMapping("/exercises/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String exercisesAdd(Model model) {
        model.addAttribute("title", "Добавление упражнения");
        return "exercises-add";
    }

    @GetMapping("/exercises/{id}")
    public String exercisesDetails(@PathVariable(value = "id") long id, Model model) {
        if (!exerciseRepository.existsById(id)) {
            return "redirect:/exercises";
        }

        Optional<Exercise> exercise = exerciseRepository.findById(id);
        ArrayList<Exercise> res = new ArrayList<>();
        exercise.ifPresent(res::add);
        model.addAttribute("exercise", res);
        return "exercise-details";
    }

    @GetMapping("/exercises/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String exercisesEdit(@PathVariable(value = "id") long id, Model model) {
        if (!exerciseRepository.existsById(id)) {
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
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String exercisesAdd(@RequestParam String title,
                               @RequestParam String anons,
                               @RequestParam String howToDoIt,
                               @RequestParam String howNotToDoIt,
                               @RequestParam String advantages,
                               @RequestParam String contraindications,
                               @RequestParam String complication,
                               @RequestParam("mainPhotoPath") MultipartFile multipartFile) throws IOException {

        String photoFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + photoFileName;

        Exercise exercise = new Exercise(title, anons, howToDoIt, howNotToDoIt, advantages, contraindications, complication, resultFileName);
        exerciseRepository.save(exercise);

        String dir = exercisesPhotoPath + "/" + exercise.getId();
        FileUploadUtil.saveFile(dir, resultFileName, multipartFile);

        return "redirect:/exercises";
    }

    @PostMapping("/exercises/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String exercisesUpdate(@PathVariable(value = "id") long id,
                                  @RequestParam String title,
                                  @RequestParam String anons,
                                  @RequestParam String howToDoIt,
                                  @RequestParam String howNotToDoIt,
                                  @RequestParam String advantages,
                                  @RequestParam String contraindications,
                                  @RequestParam String complication,
                                  @RequestParam("mainPhotoPath") MultipartFile multipartFile) throws IOException {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();
        exercise.setTitle(title);
        exercise.setAnons(anons);
        exercise.setAdvantages(advantages);
        exercise.setComplication(complication);
        exercise.setContraindications(contraindications);
        exercise.setHowNotToDoIt(howNotToDoIt);
        exercise.setHowToDoIt(howToDoIt);

        String dir = exercisesPhotoPath + "/" + exercise.getId();

        FileUploadUtil.deleteFile(dir, exercise.getMainPhotoPath());

        String photoFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + photoFileName;
        exercise.setMainPhotoPath(resultFileName);

        FileUploadUtil.saveFile(dir, resultFileName, multipartFile);

        exerciseRepository.save(exercise);
        return "redirect:/exercises/" + id;
    }

    @PostMapping("/exercises/{id}/remove")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    public String exercisesDelete(@PathVariable(value = "id") long id, Model model) throws IOException {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow();

        String dir = exercisesPhotoPath + "/" + exercise.getId();
        FileUtils.deleteDirectory(new File(dir));

        exerciseRepository.delete(exercise);
        return "redirect:/exercises/";
    }
}
