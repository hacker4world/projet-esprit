package com.example.futurbe.repositorys.AssociatifsRepo;

import com.example.futurbe.entitys.AssociatifsEntity.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {

}
