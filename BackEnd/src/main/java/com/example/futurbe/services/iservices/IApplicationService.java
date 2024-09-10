package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.ApplicationDTO;
import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Application;
import com.example.futurbe.entitys.Offre;
import com.example.futurbe.entitys.TypeOffre;
import com.example.futurbe.entitys.User;

import java.util.List;

public interface IApplicationService {
    ApplicationDTO applyToOffer(UserDTO user, OffreDto offer);
    ApplicationDTO getApplicationByOfferType(TypeOffre typeOffre) ;
    List<ApplicationDTO> getApplicationByUser(UserDTO user) ;

    List<ApplicationDTO> getApplicationByUserAndOffer(UserDTO user, OffreDto offer);
    List<ApplicationDTO> getApplicationByOffer( OffreDto offer);
    void updateIsViewed( UserDTO user,OffreDto offer);
    void deleteApplicationByOfferID( OffreDto offer);
}
