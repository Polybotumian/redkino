package com.byteopus.redkino.services;

import com.byteopus.redkino.models.Actor;
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
}

