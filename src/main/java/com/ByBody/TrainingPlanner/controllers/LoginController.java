package com.ByBody.TrainingPlanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String email(Model model) {
        model.addAttribute("title", "Авторизация пользователя");
        return "login";
    }

}