package com.byteopus.redkino.services;

import com.byteopus.redkino.models.Movie;
import com.byteopus.redkino.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public void save(Movie movie) {
        this.movieRepository.save(movie);
    }
    public List<Movie> getAll() {
        return this.movieRepository.findAll();
    }
    public void deleteById(Long id)
    {
        this.movieRepository.deleteById(id);
    }
    public List<Movie> searchByName(String name) {
        return this.movieRepository.searchByName(name);
    }
}

