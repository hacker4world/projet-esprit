package com.example.futurbe.repositorys.AssociatifsRepo;

import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    List<ClubMember> findByClubId(Long clubId);
}
