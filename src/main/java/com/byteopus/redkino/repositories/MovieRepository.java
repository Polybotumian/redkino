package com.byteopus.redkino.repositories;


import com.byteopus.redkino.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByName(String name);
    @Query("SELECT movie FROM Movie movie WHERE LOWER(movie.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Movie> searchByName(@Param("query") String query);
}