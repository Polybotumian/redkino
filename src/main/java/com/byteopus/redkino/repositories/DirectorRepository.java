package com.byteopus.redkino.repositories;


import com.byteopus.redkino.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findByName(String name);
}