package com.byteopus.redkino.services;

import com.byteopus.redkino.models.Director;
import com.byteopus.redkino.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DirectorService {
    private final DirectorRepository directorRepository;
    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }
    public void save(Director director) {
        directorRepository.save(director);
    }
    public List<Director> getAll() {
        return directorRepository.findAll();
    }
    public void deleteById(Long id)
    {
        this.directorRepository.deleteById(id);
    }
    public List<Director> searchByName(String name) {
        return this.directorRepository.searchByName(name);
    }
}

