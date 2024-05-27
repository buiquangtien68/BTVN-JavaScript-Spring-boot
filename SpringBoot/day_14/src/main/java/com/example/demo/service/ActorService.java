package com.example.demo.service;

import com.example.demo.entities.Actor;
import com.example.demo.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public List<Actor> getAllActorById(List<Integer> ids) {
        return actorRepository.findAllById(ids);
    }
}
