package com.example.futurbe.repositorys.AssociatifsRepo;

import com.example.futurbe.entitys.AssociatifsEntity.EventRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRegisterRepository extends JpaRepository<EventRegister, Long> {

    List<EventRegister> findByEventId(Long eventId);

}
