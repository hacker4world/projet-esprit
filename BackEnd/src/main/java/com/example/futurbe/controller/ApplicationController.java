package com.example.futurbe.controller;

import com.example.futurbe.dto.ApplicationDTO;
import com.example.futurbe.dto.OffreDto;
import com.example.futurbe.dto.userDTO.UserDTO;
import com.example.futurbe.entitys.Application;
import com.example.futurbe.entitys.Offre;
import com.example.futurbe.entitys.TypeOffre;
import com.example.futurbe.entitys.User;
import com.example.futurbe.services.iservices.IApplicationService;
import com.example.futurbe.services.iservices.IOffreService;
import com.example.futurbe.services.iservices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOffreService offerService;

    @PostMapping("/apply")
    public ApplicationDTO applyToOffer(@RequestParam Long userId, @RequestParam Long offerId) {
        UserDTO user = userService.getUserById(userId);
        OffreDto offer = offerService.getOffre(offerId);
        return applicationService.applyToOffer(user, offer);
    }

    @GetMapping("/typeOffer/{Type}")
    public  ApplicationDTO getApplicationsByOfferType (@RequestParam TypeOffre typeOffre)
    {
        return  applicationService.getApplicationByOfferType(typeOffre);
    }

    @GetMapping("/MyApplications")
    public List<ApplicationDTO> getApplicationsOfCurrentUser (@RequestParam Long userID)
    {        UserDTO user = userService.getUserById(userID);

        return  applicationService.getApplicationByUser(user);
    }
    @GetMapping("/applicationsByPK")
    public List<ApplicationDTO> getApplicationsByOffreUser (@RequestParam Long userID , @RequestParam Long offerID)
    {
        UserDTO user = userService.getUserById(userID);
        OffreDto offre = offerService.getOffre(offerID);

        return  applicationService.getApplicationByUserAndOffer(user,offre);
    }
    @GetMapping("/applicationsByOffer")
    public List<ApplicationDTO> getApplicationsByOffre ( @RequestParam Long offerID)
    {
        OffreDto offre = offerService.getOffre(offerID);

        return  applicationService.getApplicationByOffer(offre);
    }

    @PutMapping("/updateIsViewed")
    public void updateIsViewed(@RequestParam Long userId, @RequestParam Long offerId) {
        UserDTO user = userService.getUserById(userId);
        OffreDto offer = offerService.getOffre(offerId);
         applicationService.updateIsViewed(user, offer);
    }

    @DeleteMapping("/DeleteByOfferID")
    public void deleteApplicationByOfferID(@RequestParam Long offerId) {
        OffreDto offer = offerService.getOffre(offerId);
        applicationService.deleteApplicationByOfferID(offer);
    }
}
