package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.Actor;
import com.byteopus.redkino.models.Category;
import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.services.ActorService;
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
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;
    private final ActorService actorService;

    @Autowired
    public MovieController(MovieService movieService, CategoryService categoryService, ActorService actorService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
        this.actorService = actorService;
    }

    @GetMapping("/movies")
    public String showMovies(Model model) {
        List<Movie> allMovies = movieService.getAll();
        List<Category> allCategories = categoryService.getAll(); // Replace with your actual category service
        List<Actor> allActors = actorService.getAll(); // Replace with your actual actor service

        model.addAttribute("pageTitle", "Movies");
        model.addAttribute("movies", allMovies);
        model.addAttribute("categories", allCategories);
        model.addAttribute("actors", allActors);

        return "movies";
    }

    @PostMapping("/movies/add")
    public String addMovie(Movie movie, RedirectAttributes redirectAttributes) {
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("successMessage", "Movie added successfully");
        return "redirect:/movies";
    }

    @PostMapping("/movies/update")
    public String updateMovie(Movie movie, RedirectAttributes redirectAttributes) {
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("successMessage", "Movie updated successfully");
        return "redirect:/movies";
    }

    @PostMapping("/movies/delete")
    public String deleteMovie(Long id, RedirectAttributes redirectAttributes) {
        movieService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Movie deleted successfully");
        return "redirect:/movies";
    }

    @PostMapping("/movies/search")
    public String searchMovies(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Movie> searchResults = movieService.searchByName(query);
        List<Movie> allMovies = movieService.getAll();
        List<Category> allCategories = categoryService.getAll(); // Replace with your actual category service
        List<Actor> allActors = actorService.getAll(); // Replace with your actual actor service

        model.addAttribute("pageTitle", "Movies");
        model.addAttribute("categories", allCategories);
        model.addAttribute("actors", allActors);
        model.addAttribute("movies", searchResults);
        return "/movies";
    }

}
