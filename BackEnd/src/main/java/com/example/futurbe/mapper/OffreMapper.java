package com.example.futurbe.mapper;

import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.entitys.Offre;
import com.example.futurbe.entitys.User;
import org.springframework.stereotype.Component;

@Component
public class OffreMapper {

    public OffreDto toDto(Offre offre) {
        if (offre == null) {
            return null;
        }

        OffreDto offreDto = new OffreDto();
        offreDto.setId(offre.getId());
        offreDto.setTitre(offre.getTitre());
        offreDto.setDescription(offre.getDescription());
        offreDto.setDatePublication(offre.getDatePublication());
        offreDto.setTypeOffre(offre.getTypeOffre());
        offreDto.setDateMaj(offre.getDateMaj());
        offreDto.setStillAvailable(offre.isStillAvailable());
        User utilisateur = offre.getUser();
        if (utilisateur != null) {
            offreDto.setUtilisateurId(utilisateur.getId());
            offreDto.setUtilisateurNom(utilisateur.getFirstName());
        }

        return offreDto;
    }

    public Offre toEntity(OffreDto offreDto) {
        if (offreDto == null) {
            return null;
        }

        Offre offre = new Offre();
        offre.setId(offreDto.getId());
        offre.setTitre(offreDto.getTitre());
        offre.setDescription(offreDto.getDescription());
        offre.setDatePublication(offreDto.getDatePublication());
        offre.setTypeOffre(offreDto.getTypeOffre());
        offre.setDateMaj(offreDto.getDateMaj());
        offre.setStillAvailable(offreDto.getIsStillAvailable());
        User utilisateur = new User();
        utilisateur.setId(offreDto.getUtilisateurId());
        utilisateur.setFirstName(offreDto.getUtilisateurNom());
        offre.setUser(utilisateur);

        return offre;
    }
}

