package com.byteopus.redkino.controllers;

import com.byteopus.redkino.models.Actor;
import com.byteopus.redkino.models.Director;
import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.services.ActorService;
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
public class ActorController {

    private final MovieService movieService;
    private final ActorService actorService;

    @Autowired
    public ActorController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public String showActors(Model model) {
        List<Movie> allMovies = movieService.getAll();
        List<Actor> allDirectors = actorService.getAll();

        model.addAttribute("pageTitle", "Actors");
        model.addAttribute("movies", allMovies);
        model.addAttribute("actors", allDirectors);

        return "actors";
    }

    @PostMapping("/actors/add")
    public String addActor(Actor actor, RedirectAttributes redirectAttributes) {
        actorService.save(actor);
        redirectAttributes.addFlashAttribute("successMessage", "Actor added successfully");
        return "redirect:/actors";
    }

    @PostMapping("/actors/update")
    public String updateActor(Actor actor, RedirectAttributes redirectAttributes) {
        actorService.save(actor);
        redirectAttributes.addFlashAttribute("successMessage", "Actor updated successfully");
        return "redirect:/actors";
    }

    @PostMapping("/actors/delete")
    public String deleteActor(Long id, RedirectAttributes redirectAttributes) {
        Actor selectedActor = actorService.findById(id);

        // Remove associated entries in movie_categories
        selectedActor.getMovies().forEach(movie -> {
            movie.getActors().remove(selectedActor);
            movieService.save(movie);
        });

        // Now, delete the category
        actorService.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully");
        return "redirect:/actors";
    }

    @PostMapping("/actors/search")
    public String searchActors(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Actor> searchResults = actorService.searchByName(query);
        List<Movie> allMovies = movieService.getAll();

        model.addAttribute("pageTitle", "Actors");
        model.addAttribute("movies", allMovies);
        model.addAttribute("actors", searchResults);
        return "actors";
    }

}
