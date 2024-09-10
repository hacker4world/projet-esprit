package com.example.futurbe.services.AssociatifsServices;

import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import com.example.futurbe.repositorys.AssociatifsRepo.ClubMemberRepository;
import com.example.futurbe.services.iservices.AssociatifsIServices.IClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClubMemberService implements IClubMemberService {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Override
    public List<ClubMember> getAllMembers() {
        return clubMemberRepository.findAll();
    }

    @Override
    public Optional<ClubMember> getMemberById(Long id) {
        return clubMemberRepository.findById(id);
    }

    @Override
    public ClubMember saveMember(ClubMember member) {
        return clubMemberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        clubMemberRepository.deleteById(id);
    }
}