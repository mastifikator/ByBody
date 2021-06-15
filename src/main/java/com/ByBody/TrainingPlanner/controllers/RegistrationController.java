package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.Role;
import com.ByBody.TrainingPlanner.models.Status;
import com.ByBody.TrainingPlanner.models.User;
import com.ByBody.TrainingPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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

        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());

        if(userFromDb.isPresent()){
            model.put("message", "Пользователь существует!");
            return "redirect:/registration";
        }

        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);

        userRepository.save(user);

        return "redirect:/login";
    }

}