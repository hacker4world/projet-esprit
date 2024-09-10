package com.example.futurbe.controller.AssociatifsController;

import com.example.futurbe.entitys.AssociatifsEntity.EventRegister;
import com.example.futurbe.services.AssociatifsServices.EventRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event-registers")
public class EventRegisterController {

    @Autowired
    private EventRegisterService eventRegisterService;

    @GetMapping
    public ResponseEntity<List<EventRegister>> getAllRegisters() {
        List<EventRegister> registers = eventRegisterService.getAllRegisters();
        return new ResponseEntity<>(registers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventRegister> getRegisterById(@PathVariable Long id) {
        Optional<EventRegister> register = eventRegisterService.getRegisterById(id);
        return register.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventRegister> createRegister(@RequestBody EventRegister register) {
        EventRegister createdRegister = eventRegisterService.saveRegister(register);
        return new ResponseEntity<>(createdRegister, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventRegister> updateRegister(@PathVariable Long id, @RequestBody EventRegister register) {
        if (!eventRegisterService.getRegisterById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        register.setId(id);
        EventRegister updatedRegister = eventRegisterService.saveRegister(register);
        return new ResponseEntity<>(updatedRegister, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegister(@PathVariable Long id) {
        if (!eventRegisterService.getRegisterById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        eventRegisterService.deleteRegister(id);
        return ResponseEntity.noContent().build();
    }
}
