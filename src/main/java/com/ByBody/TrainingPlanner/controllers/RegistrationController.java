package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.Role;
import com.ByBody.TrainingPlanner.models.User;
import com.ByBody.TrainingPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("title", "Регистрация пользователя");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepository.findByEmail(user.getEmail());

        if(userFromDb != null){
            model.put("message", "User exists!");
        }

        userRepository.save(user);
        return "redirect:/login";
    }

}