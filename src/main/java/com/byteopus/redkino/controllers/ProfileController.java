package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.User;
import com.byteopus.redkino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("pageTitle", "Profile");
        return "profile";
    }
}
