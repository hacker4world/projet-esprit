package com.example.futurbe.services;

import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.entitys.Offre;
import com.example.futurbe.mapper.OffreMapper;
import com.example.futurbe.repositorys.OffreRepository;
import com.example.futurbe.services.iservices.IOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffreService implements IOffreService {
    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private OffreMapper offreMapper;

    @Override
    public OffreDto createOffre(OffreDto offreDto) {
        Offre offre = offreMapper.toEntity(offreDto);
        Offre savedOffre = offreRepository.save(offre);
        return offreMapper.toDto(savedOffre);
    }

    @Override
    public OffreDto getOffre(Long id) {
        Offre offre = offreRepository.findById(id).get();
        return offreMapper.toDto(offre);
    }

    @Override
    public List<OffreDto> getAllOffres() {
        List<Offre> offres = offreRepository.findAll();
        return offres.stream().map(offreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public OffreDto updateOffre(Long id, OffreDto offreDto) {
        Offre offre = offreMapper.toEntity(offreDto);
        Offre updatedOffre = offreRepository.save(offre);
        return offreMapper.toDto(updatedOffre);
    }



    @Override
    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }
}