package com.byteopus.redkino.controllers;

import com.byteopus.redkino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }
}
