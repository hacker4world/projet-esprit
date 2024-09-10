package com.example.futurbe.controller.AssociatifsController;

import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import com.example.futurbe.services.AssociatifsServices.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club-members")
public class ClubMemberController {

    @Autowired
    private ClubMemberService clubMemberService;

    @GetMapping
    public ResponseEntity<List<ClubMember>> getAllMembers() {
        List<ClubMember> members = clubMemberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMember> getMemberById(@PathVariable Long id) {
        Optional<ClubMember> member = clubMemberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClubMember> createMember(@RequestBody ClubMember member) {
        ClubMember createdMember = clubMemberService.saveMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubMember> updateMember(@PathVariable Long id, @RequestBody ClubMember member) {
        if (!clubMemberService.getMemberById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        member.setId(id);
        ClubMember updatedMember = clubMemberService.saveMember(member);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (!clubMemberService.getMemberById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        clubMemberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
