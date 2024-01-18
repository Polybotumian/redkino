package com.byteopus.redkino.repositories;


import com.byteopus.redkino.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findById(Long id);
    @Query("SELECT director FROM Director director WHERE LOWER(director.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Director> searchByName(@Param("query") String query);
    Optional<Director> findByName(String name);
}