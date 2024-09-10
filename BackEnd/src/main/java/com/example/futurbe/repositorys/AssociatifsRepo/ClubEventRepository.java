package com.example.futurbe.repositorys.AssociatifsRepo;


import com.example.futurbe.entitys.AssociatifsEntity.ClubEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubEventRepository extends JpaRepository<ClubEvent, Long> {
}
