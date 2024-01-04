package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.User;
import com.byteopus.redkino.models.Role;
import com.byteopus.redkino.services.RoleService;
import com.byteopus.redkino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("pageTitle", "Register");
        model.addAttribute("user", new User());
        model.addAttribute("password2", "");
        model.addAttribute("agreement", false);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
        if (
                !userService.existsByEmail(user.getEmail())
                && !userService.existsByName(user.getName())
//                && Objects.equals(model.getAttribute("password2"), user.getPassword())
//                && Objects.equals(model.getAttribute("agreement"), true)
        ) {
            BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
            user.setPassword(crypt.encode(user.getPassword()));

            List<Role> roles = new ArrayList<>();
            roles.add(roleService.findByName("USER"));
            user.setRoles(roles);
            userService.save(user);

            // Clear sensitive information from flash attributes
            redirectAttributes.addFlashAttribute("registrationSuccess", true);
            model.asMap().clear();

            // Redirect to a meaningful page
            return "redirect:/login";
        }

        // Clear sensitive information from flash attributes
        redirectAttributes.addFlashAttribute("registrationSuccess", false);
        model.asMap().clear();

        return "redirect:/register";
    }
}
