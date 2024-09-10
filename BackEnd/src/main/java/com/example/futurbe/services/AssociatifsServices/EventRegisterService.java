package com.example.futurbe.services.AssociatifsServices;

import com.example.futurbe.entitys.AssociatifsEntity.EventRegister;
import com.example.futurbe.repositorys.AssociatifsRepo.EventRegisterRepository;
import com.example.futurbe.services.iservices.AssociatifsIServices.IEventRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventRegisterService implements IEventRegisterService {

    @Autowired
    private EventRegisterRepository eventRegisterRepository;

    @Override
    public List<EventRegister> getAllRegisters() {
        return eventRegisterRepository.findAll();
    }

    @Override
    public Optional<EventRegister> getRegisterById(Long id) {
        return eventRegisterRepository.findById(id);
    }

    @Override
    public EventRegister saveRegister(EventRegister register) {
        return eventRegisterRepository.save(register);
    }

    @Override
    public void deleteRegister(Long id) {
        eventRegisterRepository.deleteById(id);
    }
}