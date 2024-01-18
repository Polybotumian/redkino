package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.*;
import com.byteopus.redkino.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> allUsers = userService.getAll();
        List<Role> allRoles = roleService.getAll();

        model.addAttribute("pageTitle", "Users");
        model.addAttribute("users", allUsers);
        model.addAttribute("roles", allRoles);

        return "users";
    }

    @PostMapping("/users/add")
    public String addUser(User user, RedirectAttributes redirectAttributes) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User added successfully");
        return "redirect:/users";
    }

    @PostMapping("/users/update")
    public String updateUser(User user, RedirectAttributes redirectAttributes) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
        return "redirect:/users";
    }

    @PostMapping("/users/delete")
    public String deleteUser(Long id, RedirectAttributes redirectAttributes) {
//        User selectedUser = userService.findById(id);
        userService.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully");
        return "redirect:/directors";
    }

    @PostMapping("/users/search")
    public String searchUsers(@RequestParam(name = "query", required = false) String query, Model model) {
        List<User> searchResults = userService.searchByName(query);
        List<Role> allRoles = roleService.getAll();

        model.addAttribute("pageTitle", "Users");
        model.addAttribute("users", searchResults);
        model.addAttribute("roles", allRoles);
        return "users";
    }

}
