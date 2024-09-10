package com.example.futurbe.controller.AssociatifsController;

import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import com.example.futurbe.entitys.User;
import com.example.futurbe.services.AssociatifsServices.ClubEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club-events")
public class ClubEventController {

    @Autowired
    private ClubEventService clubEventService;

    @GetMapping
    public ResponseEntity<List<ClubEvent>> getAllEvents() {
        List<ClubEvent> events = clubEventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubEvent> getEventById(@PathVariable Long id) {
        Optional<ClubEvent> event = clubEventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClubEvent> createEvent(@RequestBody ClubEvent event) {
        ClubEvent createdEvent = clubEventService.saveEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubEvent> updateEvent(@PathVariable Long id, @RequestBody ClubEvent event) {
        if (!clubEventService.getEventById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        event.setId(id);
        ClubEvent updatedEvent = clubEventService.saveEvent(event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (!clubEventService.getEventById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clubEventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<User>> getParticipantsByEvent(@PathVariable Long id) {
        List<User> participants = ClubEventService.getParticipantsByEvent(id);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
}
