package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.Category;
import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.services.CategoryService;
import com.byteopus.redkino.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String showCategories(Model model) {
        List<Movie> allMovies = movieService.getAll();
        List<Category> allCategories = categoryService.getAll();

        model.addAttribute("pageTitle", "Categories");
        model.addAttribute("movies", allMovies);
        model.addAttribute("categories", allCategories);

        return "categories";
    }

    @PostMapping("/categories/add")
    public String addCategory(Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Category added successfully");
        return "redirect:/categories";
    }

    @PostMapping("/categories/update")
    public String updateCategory(Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully");
        return "redirect:/categories";
    }

    @PostMapping("/categories/delete")
    public String deleteCategory(Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully");
        return "redirect:/categories";
    }

    @PostMapping("/categories/search")
    public String searchCategories(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Category> searchResults = categoryService.searchByName(query);
        List<Movie> allMovies = movieService.getAll();

        model.addAttribute("pageTitle", "Categories");
        model.addAttribute("movies", allMovies);
        model.addAttribute("categories", searchResults);
        return "/categories";
    }

}
