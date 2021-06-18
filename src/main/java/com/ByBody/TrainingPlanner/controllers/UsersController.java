package com.ByBody.TrainingPlanner.controllers;

import com.ByBody.TrainingPlanner.models.Role;
import com.ByBody.TrainingPlanner.models.User;
import com.ByBody.TrainingPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("title", "Список пользователей");
        return "users";
    }

    @GetMapping("/users/{id}")
    public String usersEdit(@PathVariable(value = "id") long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/users";
        }

        model.addAttribute("title", "Редактирование пользователя");
        model.addAttribute("roles", Role.values());

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> usr = new ArrayList<>();
        user.ifPresent(usr::add);
        model.addAttribute("user", usr);
        return "user-edit";
    }

    @PostMapping("/users/{id}")
    public String usersUpdate(@PathVariable(value = "id") long id,
                              @RequestParam String username,
                              @RequestParam String email,
                              @RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam Optional<String> isAct,
                              @RequestParam HashMap<String, String> elements) {
        System.out.println(elements);

        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        if(isAct.isPresent()){
            user.setActive(true);
        }else{
            user.setActive(false);
        }

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        for(String key : elements.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);
        return "redirect:/users";
    }


}
