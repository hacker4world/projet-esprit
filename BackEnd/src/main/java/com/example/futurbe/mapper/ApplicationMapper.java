package com.example.futurbe.mapper;
import com.example.futurbe.dto.ApplicationDTO;
import com.example.futurbe.entitys.Application;
import org.springframework.stereotype.Component;

@Component

public class ApplicationMapper {

    public  ApplicationDTO toDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(application.getId());
        dto.setOfferId(application.getOffer().getId()); // Assuming Offer has an id field
        dto.setUserId(application.getUser().getId());   // Assuming User has an id field
        dto.setApplicationDate(application.getApplicationDate());
        dto.setIs_viewed(application.is_viewed());
        return dto;
    }

    public static Application toEntity(ApplicationDTO dto) {
        Application entity = new Application();
        entity.setId(dto.getId());
        // Assuming you have appropriate constructors/setters in Application entity
        // and you need to set Offer and User entities based on offerId and userId from dto
        entity.setApplicationDate(dto.getApplicationDate());
        entity.set_viewed(dto.isIs_viewed());
        return entity;
    }
}

