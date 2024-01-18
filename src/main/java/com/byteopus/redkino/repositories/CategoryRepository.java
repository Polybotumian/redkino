package com.byteopus.redkino.repositories;


import com.byteopus.redkino.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    Optional<Category> findById(long id);
    @Query("SELECT category FROM Category category WHERE LOWER(category.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Category> searchByName(@Param("query") String query);
}