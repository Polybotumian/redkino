package com.byteopus.redkino.services;

import com.byteopus.redkino.models.Actor;
import com.byteopus.redkino.models.Director;
import com.byteopus.redkino.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActorService {
    private final ActorRepository actorRepository;
    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
    public void save(Actor actor) {
        actorRepository.save(actor);
    }
    public List<Actor> getAll() {
        return actorRepository.findAll();
    }
    public void deleteById(Long id)
    {
        this.actorRepository.deleteById(id);
    }
    public List<Actor> searchByName(String name) {
        return this.actorRepository.searchByName(name);
    }

    public Actor findById(Long actorId) {
        return this.actorRepository.findById(actorId).orElse(null);
    }
}

