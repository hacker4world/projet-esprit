package com.example.futurbe.services.iservices.AssociatifsIServices;


import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;

import java.util.List;
import java.util.Optional;

public interface IClubMemberService {
    List<ClubMember> getAllMembers();
    Optional<ClubMember> getMemberById(Long id);
    ClubMember saveMember(ClubMember member);
    void deleteMember(Long id);
}
