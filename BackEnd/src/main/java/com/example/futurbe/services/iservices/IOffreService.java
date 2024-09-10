package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.entitys.Offre;

import java.util.List;

public interface IOffreService {
    OffreDto createOffre(OffreDto offre);
    OffreDto getOffre(Long id);
    List<OffreDto> getAllOffres();
    OffreDto updateOffre(Long id, OffreDto offreDetails);
    void deleteOffre(Long id);
}
