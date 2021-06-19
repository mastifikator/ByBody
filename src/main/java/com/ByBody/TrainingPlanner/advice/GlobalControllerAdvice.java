package com.ByBody.TrainingPlanner.advice;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ModelAttribute
    public void globalAttributes(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if(userName.equals("anonymousUser")) {
            model.addAttribute("currentUser", "Аноним");
            model.addAttribute("notAuth", true);
        }else{
            model.addAttribute("currentUser", userName);
            model.addAttribute("notAuth", false);
        }
    }
}
