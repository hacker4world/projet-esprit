package com.example.futurbe.services.AssociatifsServices;

import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import com.example.futurbe.entitys.User;
import com.example.futurbe.repositorys.AssociatifsRepo.ClubEventRepository;
import com.example.futurbe.services.iservices.AssociatifsIServices.IClubEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubEventService implements IClubEventService {

    @Autowired
    private ClubEventRepository clubEventRepository;

    public static List<User> getParticipantsByEvent(Long id) {
        return List.of();
    }

    @Override
    public List<ClubEvent> getAllEvents() {
        return clubEventRepository.findAll();
    }

    @Override
    public Optional<ClubEvent> getEventById(Long id) {
        return clubEventRepository.findById(id);
    }

    @Override
    public ClubEvent saveEvent(ClubEvent event) {
        return clubEventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        clubEventRepository.deleteById(id);
    }
}