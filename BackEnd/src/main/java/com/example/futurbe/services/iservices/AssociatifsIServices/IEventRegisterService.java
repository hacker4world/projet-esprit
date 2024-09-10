package com.example.futurbe.services.iservices.AssociatifsIServices;



import com.example.futurbe.entitys.AssociatifsEntity.EventRegister;

import java.util.List;
import java.util.Optional;

public interface IEventRegisterService {
    List<EventRegister> getAllRegisters();
    Optional<EventRegister> getRegisterById(Long id);
    EventRegister saveRegister(EventRegister register);
    void deleteRegister(Long id);
}
