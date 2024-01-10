package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.Actor;
import com.byteopus.redkino.models.Category;
import com.byteopus.redkino.models.Director;
import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.services.ActorService;
import com.byteopus.redkino.services.CategoryService;
import com.byteopus.redkino.services.DirectorService;
import com.byteopus.redkino.services.MovieService;
import com.byteopus.redkino.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;
    private final ActorService actorService;
    private final DirectorService directorService;

    @Autowired
    public MovieController(MovieService movieService, CategoryService categoryService, ActorService actorService, DirectorService directorService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
        this.actorService = actorService;
        this.directorService = directorService;
    }

    @GetMapping("/movies")
    public String showMovies(Model model) {
        List<Movie> allMovies = movieService.getAll();
        List<Category> allCategories = categoryService.getAll();
        List<Actor> allActors = actorService.getAll();
        List<Director> allDirectors = directorService.getAll();

        model.addAttribute("pageTitle", "Movies");
        model.addAttribute("movies", allMovies);
        model.addAttribute("categories", allCategories);
        model.addAttribute("actors", allActors);
        model.addAttribute("directors", allDirectors);
        model.addAttribute("imgutil", new ImageUtil());

        return "movies";
    }

    @PostMapping("/movies/add")
    public String addMovie(Movie movie, RedirectAttributes redirectAttributes) {
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("successMessage", "Movie added successfully");
        return "redirect:/movies";
    }

    @PostMapping("/movies/update")
    public String updateMovie(Movie movie,  @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        try {
            movie.setImage(imageFile.getBytes());
            movieService.save(movie);
            redirectAttributes.addFlashAttribute("successMessage", "Movie updated successfully");
        }catch (IOException e)
        {
            redirectAttributes.addFlashAttribute("error", "Error processing the image file.");
        }
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
        List<Category> allCategories = categoryService.getAll();
        List<Actor> allActors = actorService.getAll();
        List<Director> allDirectors = directorService.getAll();

        model.addAttribute("pageTitle", "Movies");
        model.addAttribute("categories", allCategories);
        model.addAttribute("actors", allActors);
        model.addAttribute("movies", searchResults);
        model.addAttribute("directors", allDirectors);

        return "/movies";
    }

}
