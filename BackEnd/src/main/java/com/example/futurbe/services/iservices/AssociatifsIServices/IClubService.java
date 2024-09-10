package com.example.futurbe.services.iservices.AssociatifsIServices;

import com.example.futurbe.entitys.AssociatifsEntity.Club;

import java.util.List;
import java.util.Optional;

public interface IClubService {
    List<Club> getAllClubs();
    Optional<Club> getClubById(Long id);
    Club createClub(Club club);
    void deleteClub(Long id);
}
