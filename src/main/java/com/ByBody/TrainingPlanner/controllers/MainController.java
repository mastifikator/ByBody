package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userName != null) {
            model.addAttribute("currentUser", userName);
        }else{
            model.addAttribute("currentUser", "Аноним");
        }

        return "home";
    }

}