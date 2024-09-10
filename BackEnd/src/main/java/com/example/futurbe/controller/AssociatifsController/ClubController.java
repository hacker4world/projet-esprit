package com.example.futurbe.controller.AssociatifsController;

import com.example.futurbe.entitys.AssociatifsEntity.Club;
import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import com.example.futurbe.services.AssociatifsServices.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Optional<Club> club = clubService.getClubById(id);
        return club.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club createdClub = clubService.createClub(club);
        return new ResponseEntity<>(createdClub, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Club club) {
        if (!clubService.getClubById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        club.setId(id);
        Club updatedClub = clubService.createClub(club);
        return new ResponseEntity<>(updatedClub, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        if (!clubService.getClubById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/members")
    public ResponseEntity<List<ClubMember>> getMembersByClub(@PathVariable Long id) {
        List<ClubMember> members = clubService.getMembersByClub(id);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
