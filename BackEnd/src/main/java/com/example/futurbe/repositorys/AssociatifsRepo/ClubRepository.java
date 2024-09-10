package com.example.futurbe.repositorys.AssociatifsRepo;

import com.example.futurbe.entitys.AssociatifsEntity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
