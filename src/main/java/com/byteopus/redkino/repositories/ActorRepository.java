package com.byteopus.redkino.repositories;


import com.byteopus.redkino.models.Actor;
import com.byteopus.redkino.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByName(String name);
    @Query("SELECT actor FROM Actor actor WHERE LOWER(actor.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Actor> searchByName(@Param("query") String query);
}