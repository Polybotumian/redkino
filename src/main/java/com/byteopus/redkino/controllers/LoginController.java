package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.User;
import com.byteopus.redkino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    public LoginController(UserService userService) {

    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("pageTitle", "Login");
        model.addAttribute("user", new User());
        return "login";
    }
}
