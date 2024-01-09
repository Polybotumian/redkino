package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.Director;
import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.services.DirectorService;
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
public class DirectorController {

    private final MovieService movieService;
    private final DirectorService directorService;

    @Autowired
    public DirectorController(MovieService movieService, DirectorService directorService) {
        this.movieService = movieService;
        this.directorService = directorService;
    }

    @GetMapping("/directors")
    public String showDirectors(Model model) {
        List<Movie> allMovies = movieService.getAll();
        List<Director> allDirectors = directorService.getAll();

        model.addAttribute("pageTitle", "Directors");
        model.addAttribute("movies", allMovies);
        model.addAttribute("directors", allDirectors);

        return "directors";
    }

    @PostMapping("/directors/add")
    public String addDirector(Director director, RedirectAttributes redirectAttributes) {
        directorService.save(director);
        redirectAttributes.addFlashAttribute("successMessage", "Director added successfully");
        return "redirect:/directors";
    }

    @PostMapping("/directors/update")
    public String updateDirector(Director director, RedirectAttributes redirectAttributes) {
        directorService.save(director);
        redirectAttributes.addFlashAttribute("successMessage", "Director updated successfully");
        return "redirect:/directors";
    }

    @PostMapping("/directors/delete")
    public String deleteDirector(Long id, RedirectAttributes redirectAttributes) {
        directorService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Director deleted successfully");
        return "redirect:/directors";
    }

    @PostMapping("/directors/search")
    public String searchDirectors(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Director> searchResults = directorService.searchByName(query);
        List<Movie> allMovies = movieService.getAll();

        model.addAttribute("pageTitle", "Movies");
        model.addAttribute("movies", allMovies);
        model.addAttribute("directors", searchResults);
        return "/directors";
    }

}
