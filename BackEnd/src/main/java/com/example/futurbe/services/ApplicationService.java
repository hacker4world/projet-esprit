package com.example.futurbe.services;

import com.example.futurbe.dto.ApplicationDTO;
import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Application;
import com.example.futurbe.entitys.Offre;
import com.example.futurbe.entitys.TypeOffre;
import com.example.futurbe.entitys.User;
import com.example.futurbe.mapper.ApplicationMapper;
import com.example.futurbe.mapper.OffreMapper;
import com.example.futurbe.mapper.UserMapper;
import com.example.futurbe.repositorys.ApplicationRepository;
import com.example.futurbe.services.iservices.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired

    private ApplicationMapper applicationMapper;
    @Autowired

    private UserMapper userMapper;
    @Autowired

    private OffreMapper offreMapper;
    @Autowired
    private EmailService emailService;

    public ApplicationDTO applyToOffer(UserDTO user, OffreDto offer) {
        User userEntity= userMapper.convertToEntity(user) ;
        Offre offerEntity = offreMapper.toEntity(offer) ;
        Application application = new Application();
        application.setUser(userEntity);
        application.setOffer(offerEntity);
        application.setApplicationDate(new Date());
        return applicationMapper.toDTO(applicationRepository.save(application));
    }

    @Override
    public ApplicationDTO getApplicationByOfferType(TypeOffre typeOffre) {
        return applicationMapper.toDTO(applicationRepository.findApplicationByOffer_TypeOffre(typeOffre));
    }

    @Override
    public List<ApplicationDTO> getApplicationByUser(UserDTO user) {
        User userEntity = userMapper.convertToEntity(user);
        List<Application> applications = applicationRepository.findApplicationByUser(userEntity);
        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<ApplicationDTO> getApplicationByUserAndOffer(UserDTO user , OffreDto offer) {
        User userEntity = userMapper.convertToEntity(user);
        Offre offerEntity = offreMapper.toEntity(offer);
        List<Application> applications = applicationRepository.findApplicationByUserAndOffer(userEntity,offerEntity);
        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> getApplicationByOffer(OffreDto offer) {
        Offre offerEntity = offreMapper.toEntity(offer);
        List<Application> applications = applicationRepository.findApplicationByOffer(offerEntity);
        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateIsViewed(UserDTO user, OffreDto offer) {
        applicationRepository.updateIsViewed(user.getId(), offer.getId());
        emailService.sendEmail(user.getEmail(),"Application vue par le recureteur","Application vue par le recureteur");
    }

    @Override
    public void deleteApplicationByOfferID(OffreDto offer) {
        applicationRepository.deleteApplicationByOfferID(offer.getId());
    }
}
